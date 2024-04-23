package com.example.ivcdancer.controller.video;


import com.example.ivcdancer.config.filter.VideoHttpRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/video/")
public class VideoPlayerController {

    String url = "D:\\Projects\\digital-dancer-webside\\ivcdancer\\src\\main\\resources\\static\\videos\\test_resource.mp4";

    @Autowired
    private VideoHttpRequestHandler videoHttpRequestHandler;

    @GetMapping("/player")
    public void getPlayResource(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Path path = Paths.get(url );
        if (Files.exists(path)) {
            String mimeType = Files.probeContentType(path);
            if (!StringUtils.isEmpty(mimeType)) {
                response.setContentType(mimeType);
            }
            request.setAttribute(VideoHttpRequestHandler.ATTR_FILE, path);
            videoHttpRequestHandler.handleRequest(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
    }
}
