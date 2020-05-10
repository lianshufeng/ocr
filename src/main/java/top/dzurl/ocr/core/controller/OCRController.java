package top.dzurl.ocr.core.controller;

import lombok.Cleanup;
import lombok.SneakyThrows;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;
import top.dzurl.ocr.core.helper.TesseractHelper;
import top.dzurl.ocr.core.model.OCRModel;
import top.dzurl.ocr.core.util.ImageUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
public class OCRController {

    @Autowired
    private TesseractHelper tesseractHelper;


    private static final String RequestImageName = "img";

    /**
     * 参数为  img  支持File或base64编码
     *
     * @param request
     * @return
     */
    @SneakyThrows
    @RequestMapping("ocr")
    public OCRModel ocrFile(HttpServletRequest request) {
        @Cleanup InputStream inputStream = null;

        //优先判断表单文件
        if (ServletFileUpload.isMultipartContent(request)) {
            MultipartHttpServletRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
            MultipartFile multipartFile = multipartRequest.getFile(RequestImageName);
            if (multipartFile != null) {
                inputStream = multipartFile.getInputStream();
            }
        }

        //参数获取base64编码
        if (inputStream == null) {
            String base64 = request.getParameter(RequestImageName);
            Assert.hasText(base64, "参数 " + RequestImageName + " 不能为空");
            inputStream = new ByteArrayInputStream(ImageUtil.base64ToImage(base64));
        }
        return tesseractHelper.doOCR(inputStream);
    }


}
