package com.proj.notification.service;

import java.time.Year;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.proj.notification.config.EmailTemplateDefaultsConfig;
import com.proj.notification.config.MailConfig;
import com.proj.notification.dto.AttachmentData;
import com.proj.notification.dto.EmailRequest;
import com.proj.notification.dto.EmailTemplateVariables;
import com.proj.notification.dto.TemplateEmailRequest;
import com.proj.notification.exception.EmailSendException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final Logger log =
            LoggerFactory.getLogger(EmailServiceImpl.class);

    private static final String DEFAULT_TEMPLATE = "universal";
   
    private final EmailTemplateDefaultsConfig templateDefaults;

    private final JavaMailSender mailSender;
    private final MailConfig mailConfig;
    private final Configuration freemarkerConfig;

    // SIMPLE EMAIL 
    @Async
    @Override
    public void sendEmail(EmailRequest request) {
        validateRequest(request);

        log.info("Sending email to {}", request.getTo());

        String html = processTemplate(
                DEFAULT_TEMPLATE,
                request.getSubject(),
                request.getVariables()
        );

        sendMail(request.getTo(), request.getSubject(), html);
    }

    // EMAIL WITH ATTACHMENT
    @Async
    @Override
    public void sendEmailWithAttachment(
            EmailRequest request,
            AttachmentData attachment
    ) {
        validateRequest(request);

        log.info(
                "Sending email with attachment to {}, file={}",
                request.getTo(),
                attachment.getFileName()
        );

        String html = processTemplate(
                DEFAULT_TEMPLATE,
                request.getSubject(),
                request.getVariables()
        );

        sendMailWithAttachment(
                request.getTo(),
                request.getSubject(),
                html,
                attachment
        );
    }

    // TEMPLATE EMAIL
   @Async
    @Override
    public void sendTemplateEmail(TemplateEmailRequest request) {
        log.info(
                "Sending template email [{}] to {}",
                request.getTemplateName(),
                request.getTo()
        );

        String html = processTemplate(
                request.getTemplateName(),
                request.getSubject(),
                request.getVariables()
        );

        sendMail(request.getTo(), request.getSubject(), html);
    }

    // TEMPLATE PROCESSOR 
    private String processTemplate(
            String templateName,
            String subject,
            EmailTemplateVariables vars
    ) {
        try {
            Template template =
                    freemarkerConfig.getTemplate(
                            "email/" + templateName + ".ftl"
                    );

            Map<String, Object> model = new HashMap<>();

            if (vars != null) {
                model.put("name", vars.getName());
                model.put("message", vars.getMessage());
                model.put("highlight", vars.getHighlight());
                model.put("validity", vars.getValidity());
                model.put("actionText", vars.getActionText());
                model.put("actionUrl", vars.getActionUrl());
                model.put("referenceId", vars.getReferenceId());
                model.put("amount", vars.getAmount());
                model.put("date", vars.getDate());
                model.put("time", vars.getTime());
                model.put("supportEmail", vars.getSupportEmail());
                model.put("supportPhone", vars.getSupportPhone());
                model.put("appName", vars.getAppName());
                model.put("teamName", vars.getTeamName());
                model.put("companyName", vars.getCompanyName());
                
            }
            model.putIfAbsent("appName", templateDefaults.getAppName());
            model.putIfAbsent("tagline", templateDefaults.getTagline());
            model.putIfAbsent("teamName", templateDefaults.getTeamName());
            model.putIfAbsent("companyName", templateDefaults.getCompanyName());
            model.putIfAbsent("footerMessage", templateDefaults.getFooterMessage());

            model.putIfAbsent("subject", subject);
            model.putIfAbsent("year", Year.now().getValue());

            return FreeMarkerTemplateUtils
                    .processTemplateIntoString(template, model);

        } catch (Exception e) {
            log.error("Template processing failed", e);
            throw new RuntimeException("Template processing failed");
        }
    }

    //  MAIL SENDER 
    private void sendMail(
            String to,
            String subject,
            String body
    ) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, false);

            helper.setFrom(mailConfig.fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            mailSender.send(message);
            log.info("Email sent successfully to {}", to);

        } catch (Exception e) {
            log.error("Email sending failed to {}", to, e);
            throw new EmailSendException(
                    "Unable to send email at this moment", e
            );
        }

    }

    private void sendMailWithAttachment(
            String to,
            String subject,
            String body,
            AttachmentData attachment
    ) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true);

            helper.setFrom(mailConfig.fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            helper.addAttachment(
                    attachment.getFileName(),
                    new ByteArrayResource(attachment.getData()),
                    attachment.getContentType()
            );

            mailSender.send(message);
            log.info("Email with attachment sent to {}", to);

        }catch (Exception e) {
            log.error("Email sending failed to {}", to, e);
            throw new EmailSendException(
                    "Unable to send email at this moment", e
            );
        }

    }

    private void validateRequest(EmailRequest request) {
        if (request == null || request.getTo() == null) {
            throw new IllegalArgumentException("Email 'to' must not be null");
        }
    }
}
