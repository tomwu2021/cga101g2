package com.picture.controller;

import aws.S3Service;
import com.picture.model.PictureVO;
import com.picture.service.PictureService;
import com.util.StreamUtils;
import com.util.TransferTool;
import org.apache.catalina.Session;
import org.apache.catalina.core.ApplicationContext;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

    /**
     * 輸出位元流
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("image/jpeg");
        ServletOutputStream out = res.getOutputStream();

        Integer pictureId = Integer.parseInt(req.getParameter("pictureId"));
        String preview = req.getParameter("preview");
//        Read From S3
        System.out.println("req.getQueryString(): " + req.getQueryString());
        ServletContext context = getServletContext();
        ByteArrayOutputStream baos = (ByteArrayOutputStream) context.getAttribute(req.getQueryString());// 模擬Cache: 取值
        if (baos == null) {
            PictureVO pic = pictureService.getOne(pictureId);
            URL url;
            if ("1".equals(preview)) {
                url = new URL(pic.getPreviewUrl());
            } else {
                url = new URL(pic.getUrl());
            }
            baos = new StreamUtils().CloneInputStream(url.openStream());
            context.setAttribute(req.getQueryString(), baos); // 模擬Cache: 塞
        }
        out.write(baos.toByteArray());
        // end Read From S3


        // image From DB
        //Application Scope
        // 模擬Cache
//        ServletContext context=getServletContext();
//        byte[] imageBody = (byte[]) context.getAttribute(req.getQueryString());
//        if (imageBody.length == 0) {
//            PictureVO pic = pictureService.getOne(pictureId);
//            imageBody = pic.getBody(); //取得 blob資料
//            context.setAttribute(req.getQueryString(), imageBody);
//        }
//        //將流輸出到頁面
//        out.write(imageBody);

        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}
