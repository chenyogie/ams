package com.ares.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//对swagger进行配置
@Configuration
@EnableWebMvc
@EnableSwagger2
//扫描个包下面controller来生成接口描述
@ComponentScan(basePackages="com.ares.web.controller")
public class SwaggerConfig {
    
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ares.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    

    private ApiInfo apiInfo(){
         @SuppressWarnings("deprecation")
        ApiInfo info=new ApiInfo(
                 "档案管理系统crud测试",
                 "一个完成crud",
                 "v1",
                 "http://www.ares.cn",
                 "ares",
                 "ares",
                 "http://www.ares.cn");
         return info;
    }
}