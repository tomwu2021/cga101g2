package com.picture.service;

import com.album.model.AlbumJDBCDAO;
import com.album.model.AlbumVO;
import com.amazonaws.util.IOUtils;
import com.common.exception.JDBCException;
import com.common.model.MappingJDBCDAO;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Part;

import com.common.model.MappingTableDto;
import com.common.model.PageQuery;
import com.common.model.PageResult;
import com.picture.model.PictureJDBCDAO;
import com.picture.model.PictureVO;

import aws.S3Service;
import com.util.StreamUtils;
import connection.DruidConnection;
import connection.JDBCConnection;
import connection.JNDIConnection;

public class PictureService {
	S3Service s3Service = new S3Service();
	PictureJDBCDAO picDAO = new PictureJDBCDAO();
	MappingJDBCDAO mappingDAO = new MappingJDBCDAO();
	AlbumJDBCDAO albumDao = new AlbumJDBCDAO();

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
			System.out.println(albumId);
			System.out.println(i++);
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
					// byte[] bytes = IOUtils.toByteArray(is);
				}
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
		mappingDAO.deleteOnePictureMapping(mtd);
		picDAO.deleteById(pic2.getPictureId());
		return s3Service.deleteS3Image(pic2);
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

}
