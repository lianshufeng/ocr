package top.dzurl.ocr.boot;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan("top.dzurl.ocr.core")
@SpringBootApplication

public class OCRApplication {


    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(OCRApplication.class, args);
    }

}
