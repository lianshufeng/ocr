package top.dzurl.ocr.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OCRModel {


    /**
     * 识别的文本
     */
    private String[] text;

    /**
     * 消耗时间
     */
    private long time;

}
