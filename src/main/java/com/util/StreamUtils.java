package com.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {
    public ByteArrayOutputStream CloneInputStream(InputStream in) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            // Code simulating the copy

            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Open new InputStreams using recorded bytes
        // Can be repeated as many times as you wish
        // InputStream is1 = new ByteArrayInputStream(baos.toByteArray());
        // InputStream is2 = new ByteArrayInputStream(baos.toByteArray());
        return baos;
    }
}
