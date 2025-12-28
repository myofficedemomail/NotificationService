package com.proj.notification.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {

    @Value("${notification.mail.from}")
    public String fromEmail;
}
