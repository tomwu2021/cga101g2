package com.picture.service;

import aws.S3Service;
import com.album.model.AlbumJDBCDAO;
import com.common.model.MappingJDBCDAO;
import com.common.model.MappingTableDto;
import com.common.model.PageQuery;
import com.common.model.PageResult;
import com.picture.mapper.PictureMapper;
import com.picture.model.PictureJDBCDAO;
import com.picture.model.PictureVO;
import connection.JDBCConnection;
import connection.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PictureService {
    S3Service s3Service = new S3Service();
    PictureJDBCDAO picDAO = new PictureJDBCDAO();
    MappingJDBCDAO mappingDAO = new MappingJDBCDAO();
    AlbumJDBCDAO albumDao = new AlbumJDBCDAO();
    SqlSession session = MyBatisUtil.getSessionTest();
    PictureMapper pm = session.getMapper(PictureMapper.class);

    /**
     * 上傳圖檔, 不帶相簿ID(多為後台上傳圖檔使用)
     *
     * @param parts
     * @return 已上傳圖檔集合
     * @throws IOException
     */
    public List<PictureVO> uploadImage(Collection<Part> parts) throws IOException {
        return this.uploadImage(parts, null);
    }

    /**
     * 上傳圖檔
     *
     * @param parts   multipart/form-data POST
     * @param albumId 相簿ID
     * @return 已上傳圖檔集合
     * @throws IOException
     */
    public List<PictureVO> uploadImage(Collection<Part> parts, Integer albumId) throws IOException {
        List<PictureVO> pvs = new ArrayList<>();
        MappingTableDto mappingTableDto = new MappingTableDto();
        Connection con = JDBCConnection.getRDSConnection();
        mappingTableDto.setTableName1("photos");
        mappingTableDto.setColumn1("picture_id");
        mappingTableDto.setColumn2("album_id");
        Savepoint sp;
        try {
            con.setAutoCommit(false);
            int i = 1;
            for (Part part : parts) {
                sp = con.setSavepoint();
                PictureVO pv = new PictureVO();
                String fileName = getFileNameFromPart(part);
                if (getFileNameFromPart(part) != null && part.getContentType() != null) {
                    System.out.println(fileName);
                    InputStream in = part.getInputStream();
//                    ByteArrayOutputStream clone = new StreamUtils().CloneInputStream(in);
//                    InputStream forS3 = new ByteArrayInputStream(clone.toByteArray());
//                    InputStream forDB = new ByteArrayInputStream(clone.toByteArray());
//                    pv.setBody(IOUtils.toByteArray(forDB));
                    pv = s3Service.uploadImageToS3(in, fileName);
                    pvs.add(picDAO.insert(pv, con));
                    //byte[] bytes = IOUtils.toByteArray(is);
                }
                System.out.println(i++);
                if (albumDao.isAlbum(albumId, con) != null && pv.getPictureId() != null) {
                    mappingTableDto.setId1(pv.getPictureId());
                    mappingTableDto.setId2(albumId);
                    mappingDAO.insertOneMapping(mappingTableDto, con);
                } else if (albumId != null || pv.getPictureId() == null) {
                    con.rollback(sp);
                    if (pv.getPictureId() != null) {
                        System.err.println("start delete picture from S3 where url is : " + pv.getUrl());
                        deletePicture(pv.getPictureId());
                    }
                    System.err.println("INSERT picture failed, File name is : " + pv.getPictureId() + " :: rollback!");
                }
            }
            con.commit();
            con.setAutoCommit(true);
            con.close();
        } catch (SQLException e) {

            for (PictureVO pv : pvs) {
                deletePicture(pv.getPictureId());
            }
            e.printStackTrace();
        }
        return pvs;
    }

    /**
     * 取得會員預設相簿並上傳圖檔(多為前台使用)
     *
     * @param parts
     * @param memberId 會員ID
     * @return 已上傳圖檔集合
     * @throws IOException
     */
    public List<PictureVO> uploadImageByDefaultAlbum(Collection<Part> parts, int memberId) throws IOException {
        return this.uploadImage(parts, albumDao.selectDefaultAlbumByMemberId(memberId));
    }

    public boolean deletePicture(Integer pictureId) {
        System.out.println(pictureId);
        PictureVO pic2 = picDAO.getOneById(pictureId);
        MappingTableDto mtd = new MappingTableDto();
        mtd.setTableName1("photos");
        mtd.setId1(pictureId);
        if (mappingDAO.deleteOnePictureMapping(mtd)) {
            picDAO.deleteById(pic2.getPictureId());
        }
        System.out.println(pic2);
        return s3Service.deleteS3Image(pic2.getFileKey());
    }

    public PageResult<PictureVO> getPageResult(PageQuery pageQuery) {
        return picDAO.getPageResult(pageQuery);
    }

    public PictureVO getOne(Integer id) {
        return picDAO.getOneById(id);
    }

    String getFileNameFromPart(Part part) {
        String header = part.getHeader("content-disposition");
        System.out.println("header=" + header);
        String filename = "";
        if (header.contains("*=UTF-8")) {
            filename = new File(header.substring(header.lastIndexOf("=") + 8, header.length())).getName();
        } else {
            filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
        }
        if (filename.length() == 0) {
            return null;
        }
        return filename;

    }

    public PageResult<PictureVO> getPageResultUseMyBatis(PageQuery pageQuery) {
        int total = pm.selectCountByPageQuery(pageQuery);
        List<PictureVO> pics = pm.selectByPageQuery(pageQuery);
        return new PageResult<>(pageQuery, pics, total);
    }
}
