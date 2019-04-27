package com.lfm.stater.swagger2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.service.Contact;

@ConfigurationProperties(prefix = "swagger2")
@Data
public class Swagger2Properties {
    private boolean enable=true;
    private String title="swagger2 RESTful APIs";
    private String description="swagger-bootstrap-ui";
    private String version="1.0";
    private String basePackage="";
    private Contact concat =new Contact("","","");
}
