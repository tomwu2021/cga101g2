package com.picture.service;

import com.album.model.AlbumJDBCDAO;
import com.album.model.AlbumVO;
import com.common.model.MappingJDBCDAO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Part;

import com.common.model.MappingTableDto;
import com.picture.model.PictureJDBCDAO;
import com.picture.model.PictureVO;

import aws.S3Service;
import connection.DruidConnection;
import connection.JDBCConnection;

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
		Connection con = DruidConnection.getRDSConnection();
		mappingTableDto.setTableName1("photos");
		mappingTableDto.setColumn1("picture_id");
		mappingTableDto.setColumn2("album_id");
		Savepoint sp;
		try {
			con.setAutoCommit(false);
			sp = con.setSavepoint();
			int i =1;
			for (Part part : parts) {
				PictureVO pv = new PictureVO();
				String fileName = getFileNameFromPart(part);
				if (getFileNameFromPart(part) != null && part.getContentType() != null) {
					System.out.println(fileName);
					InputStream in = part.getInputStream();
					pv = s3Service.uploadImageToS3(in, fileName);
					pvs.add(picDAO.insert(pv, con));
				}
				System.out.println(i++);
				if (albumDao.isAlbum(albumId,con)!=null && pv.getPictureId() != null) {
					mappingTableDto.setId1(pv.getPictureId());
					mappingTableDto.setId2(albumId);
					mappingDAO.insertOneMapping(mappingTableDto, con);
				} else {
					con.rollback(sp);
				}
			}
			con.commit();
			con.setAutoCommit(true);
			con.close();
		} catch (SQLException e) {
			for(PictureVO pv:pvs) {
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
