package com.util;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;


public class ImagesUtils {
    public InputStream changeToSmallImg_w(InputStream original, String imgType, int maxWidth) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            AffineTransform transform = new AffineTransform();
            BufferedImage bis = ImageIO.read(original);
            //原来的高宽
            int w = bis.getWidth();
            int h = bis.getHeight();

            //等比例缩放　
            int nowWidth = maxWidth;
            int nowHeight = (nowWidth * h) / w;
            if (nowHeight > maxWidth) {
                nowHeight = maxWidth;
                nowWidth = (nowHeight * w) / h;
            }

            double sx = (double) nowWidth / w;
            double sy = (double) nowHeight / h;

            transform.setToScale(sx, sy);

            AffineTransformOp ato = new AffineTransformOp(transform, null);
            BufferedImage bid = new BufferedImage(nowWidth, nowHeight,
                    (imgType.compareToIgnoreCase("png") == 0)
                            ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_3BYTE_BGR);
            ato.filter(bis, bid);

            ImageIO.write(bid, imgType, os);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(os.toByteArray());
    }
}
