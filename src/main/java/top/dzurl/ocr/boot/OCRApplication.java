package top.dzurl.ocr.boot;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("top.dzurl.ocr.core")
@SpringBootApplication
public class OCRApplication extends SpringBootServletInitializer {


    /**
     * 兼容Web容器启动
     *
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(this.getClass());
    }

    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(OCRApplication.class, args);
    }

}
