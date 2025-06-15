package com.soulscribble.soulscribbles_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String absolutePath = Paths.get("audio").toFile().getAbsolutePath(); // 프로젝트/audio 폴더
        registry.addResourceHandler("/audio/**")
                .addResourceLocations("file:///" + absolutePath + "/");
    }
}
