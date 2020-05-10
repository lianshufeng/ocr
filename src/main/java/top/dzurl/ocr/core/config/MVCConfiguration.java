package top.dzurl.ocr.core.config;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 视图解析器
 */
@Log
@Configuration
@EnableWebMvc
@EnableScheduling
public class MVCConfiguration implements WebMvcConfigurer {


    @Autowired
    private ApplicationContext applicationContext;


    /**
     * 静态资源
     */
    public final static String StaticResources = "resources";


    /**
     * 跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String diskPath = ResourceUtils.FILE_URL_PREFIX + "/" + new ApplicationHome().getDir().getAbsolutePath() + "/" + StaticResources + "/";
        while (diskPath.indexOf("//") > -1) {
            diskPath = diskPath.replaceAll("//", "/");
        }
        log.info("disk : " + diskPath);
        registry.addResourceHandler("/" + StaticResources + "/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/" + StaticResources + "/")
                .addResourceLocations(diskPath)
        ;
    }


    /**
     * 异常处理
     *
     * @param resolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.addAll(this.applicationContext.getBeansOfType(HandlerExceptionResolver.class).values());
    }


    /**
     * 配置最高的文件上传
     *
     * @return
     */
    @Bean
    public CommonsMultipartResolver commonsMultipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(5242880);
        return commonsMultipartResolver;
    }

}
