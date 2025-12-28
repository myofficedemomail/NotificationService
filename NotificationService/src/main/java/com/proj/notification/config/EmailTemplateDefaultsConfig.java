package com.proj.notification.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "notification.template")
public class EmailTemplateDefaultsConfig {

    private String appName;
    private String tagline;
    private String teamName;
    private String companyName;
    private String footerMessage;
    
    private String logoPath;
    private String logoContentType;
}
