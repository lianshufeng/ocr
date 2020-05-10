package top.dzurl.ocr.core.util;

import org.springframework.util.Base64Utils;

public class ImageUtil {

    /**
     * base64编码字符串转换为图片
     */
    public static byte[] base64ToImage(String imgBase64) {
        String base64 = imgBase64;
        int at = base64.indexOf(",");
        if (at != -1) {
            base64 = base64.substring(at + 1, base64.length());
        }
        return Base64Utils.decodeFromString(base64);
    }


}
