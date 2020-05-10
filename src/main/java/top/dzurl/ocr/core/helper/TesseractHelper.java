package top.dzurl.ocr.core.helper;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import net.sourceforge.tess4j.ITesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.dzurl.ocr.core.model.OCRModel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Log
@Component
public class TesseractHelper {

    @Autowired
    private ITesseract tesseract;


    @SneakyThrows
    public OCRModel doOCR(String fileName) {
        @Cleanup FileInputStream fileInputStream = new FileInputStream(new File(fileName));
        return doOCR(fileInputStream);
    }


    @SneakyThrows
    public OCRModel doOCR(InputStream inputStream) {
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        long time = System.currentTimeMillis();
        String[] text = tesseract.doOCR(bufferedImage).replaceAll("\n\n", "\n").split("\n");
        return OCRModel.builder().text(text).time(System.currentTimeMillis() - time).build();
    }


}
