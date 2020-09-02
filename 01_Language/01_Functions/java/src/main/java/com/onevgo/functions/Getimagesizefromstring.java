package com.onevgo.functions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Getimagesizefromstring {
    public static Map<String, Object> getimagesizefromstring(byte[] imagedata) {
        Map<String, Object> res = new HashMap<>();
        InputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(imagedata);
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            res.put("width", bufferedImage.getWidth());
            res.put("height", bufferedImage.getHeight());
            res.put("type", bufferedImage.getType());
            res.put("bits", bufferedImage.getColorModel().getPixelSize());
            //res.put("channels", bufferedImage.getColorModel().getNumColorComponents());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("test.gif"));
            byte[] b = new byte[8192];
            int len;
            while ((len = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, len);
            }

            System.out.println(getimagesizefromstring(outputStream.toByteArray()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
