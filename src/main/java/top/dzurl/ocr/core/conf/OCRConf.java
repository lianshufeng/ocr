package top.dzurl.ocr.core.conf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "ocr")
public class OCRConf {


    /**
     * 数据集
     */
    private String datasetPath = "./tessdata";

    /**
     * 识别库语言 chi_sim
     */
    private String language = "chi_sim";

    /**
     * 用户的dpi
     */
    private String userDefinedDpi = "300";


}
