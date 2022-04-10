package com.picture.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Part;

import com.album.model.AlbumDAO;
import com.common.model.MappingDAO;
import com.common.model.MappingTableDto;
import com.picture.model.PictureDAO;
import com.picture.model.PictureVO;

import aws.S3Service;

public class PictureService {
	S3Service s3Service = new S3Service();
	PictureDAO picDAO = new PictureDAO();
	MappingDAO mappingDAO = new MappingDAO();
	AlbumDAO AlbumDao = new AlbumDAO();


	/**
	 * 上傳圖檔, 不帶相簿ID(多為後台上傳圖檔使用)
	 * @param parts
	 * @return 已上傳圖檔集合
	 * @throws IOException
	 */
	public List<PictureVO> uploadImage(Collection<Part> parts) throws IOException {
		return this.uploadImage(parts, null);
	}
	

	/**
	 * 上傳圖檔
	 * @param parts multipart/form-data POST 
	 * @param albumId 相簿ID
	 * @return 已上傳圖檔集合
	 * @throws IOException
	 */
	public List<PictureVO> uploadImage(Collection<Part> parts, Integer albumId) throws IOException {
		List<PictureVO> pvs = new ArrayList<>();
		MappingTableDto mappingTableDto = new MappingTableDto();
		mappingTableDto.setTableName("photo");
		mappingTableDto.setColumn1("picture_id");
		mappingTableDto.setColumn2("album_id");
		mappingTableDto.setId2(albumId);

		for (Part part : parts) {
			PictureVO pv = new PictureVO();
			String fileName = getFileNameFromPart(part);
			if (getFileNameFromPart(part) != null && part.getContentType() != null) {
				System.out.println(fileName);
				InputStream in = part.getInputStream();
				pv = s3Service.uploadImageToS3(in, fileName);
				pvs.add(pv);
				picDAO.insert(pv);
			}

			if (albumId != null && pv.getPictureId() != null) {
				mappingTableDto.setId1(pv.getPictureId());
				mappingDAO.insertOneMapping(mappingTableDto);
			}
		}

		return pvs;

	}

	/**
	 * 取得會員預設相簿並上傳圖檔(多為前台使用)
	 * @param parts 
	 * @param memberId 會員ID
	 * @return 已上傳圖檔集合
	 * @throws IOException
	 */
	public List<PictureVO> uploadImageByDefaultAlbum(Collection<Part> parts, int memberId) throws IOException {
		return this.uploadImage(parts, AlbumDao.selectDefaultAlbumByMemberId(memberId));
	}

	String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // ���ե�
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
