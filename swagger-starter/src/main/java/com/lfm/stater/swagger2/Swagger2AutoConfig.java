package com.lfm.stater.swagger2;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableConfigurationProperties(Swagger2Properties.class)
@EnableSwagger2
@Slf4j
public class Swagger2AutoConfig {

    private final Swagger2Properties properties;
    public Swagger2AutoConfig(Swagger2Properties properties){
        this.properties = properties;
    }



    @Bean
    public Docket createRestApi() {
        log.info("init swagger2...");
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(properties.isEnable())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        ApiInfoBuilder builder = new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .version("1.0");
        if (properties.getConcat() != null){
            builder.contact(properties.getConcat());
        }
        return builder.build();
    }
}
