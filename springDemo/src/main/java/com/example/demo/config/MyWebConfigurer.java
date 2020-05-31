package com.example.demo.config;

import com.example.demo.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer{
    @Bean
    public LoginInterceptor getLoginIntercepter(){
        return new LoginInterceptor() ;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 对所有的路径使用拦截器，除了 /index.html
        registry.addInterceptor(getLoginIntercepter()).addPathPatterns("/**").excludePathPatterns("/index.html");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "D:/myProject/img/");
    }
}
