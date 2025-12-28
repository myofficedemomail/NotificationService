package com.proj.notification.service;

import com.proj.notification.dto.AttachmentData;
import com.proj.notification.dto.EmailRequest;
import com.proj.notification.dto.TemplateEmailRequest;

public interface EmailService {

    void sendEmail(EmailRequest request);

    void sendEmailWithAttachment(
            EmailRequest request,
            AttachmentData attachment
    );

    void sendTemplateEmail(TemplateEmailRequest request);
}


