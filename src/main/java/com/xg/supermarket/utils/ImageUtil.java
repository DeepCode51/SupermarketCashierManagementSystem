package com.xg.supermarket.utils;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageUtil {
    public static String bufferedImageToBase64(BufferedImage bufferedImage){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "data:image/png;base64,"+Base64.encodeBase64String(stream.toByteArray());
    }
}
