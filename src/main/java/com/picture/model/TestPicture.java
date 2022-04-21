package com.picture.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.common.model.MappingTableDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import aws.S3Service;

public class TestPicture {
	
	public static void main(String[] args) {
		PictureJDBCDAO pdao = new PictureJDBCDAO();
//		PictureVO pvo = new PictureVO();
//		PictureVO pvo3 = new PictureVO();
//		List<PictureVO> picList = new ArrayList<PictureVO>();
//		pvo.setFile_key("safasf");
//		pvo.setP_url("123567");
//		pvo3.setFile_key("sdmlv;sdv");
//		pvo3.setP_url("3532642");
//		picList.add(pvo);
//		picList.add(pvo3);
//		List<PictureVO> picList2 = pdao.insertPictures(picList);
//		for(PictureVO pic2:picList2) {
//			System.out.println(pic2);
//		}
		MappingTableDto mtd =new MappingTableDto();
		mtd.setTableName1("photos");
		mtd.setColumn1("album_id");
		mtd.setId1(80);
		System.out.println(pdao.queryPicturesByMapping(mtd));
		
	}


}
