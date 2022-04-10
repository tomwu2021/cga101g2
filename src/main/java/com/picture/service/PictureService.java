package com.picture.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Part;

import com.common.model.MappingDAO;
import com.common.model.MappingTableDto;
import com.picture.model.PictureDAO;
import com.picture.model.PictureVO;

import aws.S3Service;

public class PictureService {
	S3Service s3Service = new S3Service();
	PictureDAO picDAO = new PictureDAO();
	MappingDAO mappingDAO = new MappingDAO();

	public List<PictureVO> uploadImage(Collection<Part> parts) throws IOException {
		return this.uploadImage(parts, null);
	}

	public List<PictureVO> uploadImage(Collection<Part> parts, Integer albumId) throws IOException {
		List<PictureVO> pvs = new ArrayList<>();
		MappingTableDto mappingTableDto = new MappingTableDto();
		mappingTableDto.setTableName("photo");
		mappingTableDto.setColumn1("picture_id");
		mappingTableDto.setColumn2("album_id");
		mappingTableDto.setId2(albumId);

		for (Part part : parts) {
			PictureVO pv = new PictureVO();
			String fileName = URLDecoder.decode(getFileNameFromPart(part), "UTF-8");
			if (!"".equals(fileName) && part.getContentType() != null) {
				InputStream in = part.getInputStream();
				pv = s3Service.uploadImageToS3(in, fileName);
				pvs.add(pv);
				picDAO.insert(pv);
			}

			// 新增圖片record

			// mapping 圖片 to 相簿
			if (albumId != null && pv.getPictureId() != null) {
				mappingTableDto.setId1(pv.getPictureId());
				mappingDAO.insertOneMapping(mappingTableDto);
			}
		}

		return pvs;

	}

	public List<PictureVO> uploadImageByDefaultAlbum(Collection<Part> parts, int memberId) {
		// 取得預設相簿
		// return this.uploadImage(part, AlbumDao.selectDefaultAlbum(memberId));
		return null;
	}

	String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
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
