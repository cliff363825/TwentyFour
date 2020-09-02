package com.onevgo.functions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Getimagesize {
    public static Map<String, Object> getimagesize(String filename) {
        Map<String, Object> res = new HashMap<>();
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(filename));
            res.put("width", bufferedImage.getWidth());
            res.put("height", bufferedImage.getHeight());
            res.put("type", bufferedImage.getType());
            res.put("bits", bufferedImage.getColorModel().getPixelSize());
            //res.put("channels", bufferedImage.getColorModel().getNumColorComponents());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getimagesize("test.gif"));
    }
}
