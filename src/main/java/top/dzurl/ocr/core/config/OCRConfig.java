package top.dzurl.ocr.core.config;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import top.dzurl.ocr.core.conf.OCRConf;

@Configuration
public class OCRConfig {


    @Autowired
    private OCRConf ocrConf;

    @Bean
    public ITesseract tesseract() {
        Assert.hasText(ocrConf.getDatasetPath(), "数据库目录不能为空");
        ITesseract instance = new Tesseract();
        instance.setDatapath(ocrConf.getDatasetPath());
        instance.setLanguage(ocrConf.getLanguage());
        instance.setTessVariable("user_defined_dpi", ocrConf.getUserDefinedDpi());
        return instance;
    }


}
