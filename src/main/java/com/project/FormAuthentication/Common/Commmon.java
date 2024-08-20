package com.project.FormAuthentication.Common;


import com.project.FormAuthentication.Model.TokenDatas;
import com.project.FormAuthentication.Util.JwtInterceptor;
import com.project.FormAuthentication.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Commmon implements WebMvcConfigurer {
    @Autowired
    JwtInterceptor jwtInterceptor;


    @Bean
    @RequestScope
    public TokenDatas gettokendata(){
        return new TokenDatas();
    }

    @Bean
    public JwtInterceptor JwtInterceptor(){
        return new JwtInterceptor(gettokendata());
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(jwtInterceptor);
    }
}
