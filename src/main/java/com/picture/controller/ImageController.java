package com.picture.controller;

import aws.S3Service;
import com.picture.model.PictureVO;
import com.picture.service.PictureService;
import com.util.TransferTool;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.net.URL;

@WebServlet("/image")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 10 * 1024 * 1024, maxRequestSize = 10 * 10 * 1024
        * 1024)

public class ImageController extends HttpServlet {
    PictureService pictureService = new PictureService();

    /**
     *
     */
    private static final long serialVersionUID = -6376892214189069235L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("image/jpeg");
//		res.setContentType("text/html; charset=UTF-8");
        ServletOutputStream out = res.getOutputStream();

        Integer pictureId = Integer.parseInt(req.getParameter("pictureId"));
        int preview = Integer.parseInt(req.getParameter("preview"));
        PictureVO pic = pictureService.getOne(pictureId);
        URL url;
        if(preview == 0){
            url = new URL(pic.getUrl());
        }else{
            System.out.println(pic.getPreviewUrl());
            url = new URL(pic.getPreviewUrl());
        }
        Image image = ImageIO.read(url);
        ImageIO.write((RenderedImage) image, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
    }
}
