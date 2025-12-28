package com.proj.notification.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.notification.dto.ApiResponse;
import com.proj.notification.dto.AttachmentData;
import com.proj.notification.dto.EmailRequest;
import com.proj.notification.dto.TemplateEmailRequest;
import com.proj.notification.exception.EmailSendException;
import com.proj.notification.service.EmailService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/send")
@RequiredArgsConstructor
public class EmailNotificationController {

    private static final Logger log =
            LoggerFactory.getLogger(EmailNotificationController.class);

    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    //EMAIL WITHOUT ATTACHMENT
    @Operation(summary = "Send email using universal template")
    @PostMapping("/email")
    public ApiResponse sendEmail(
            @Valid @RequestBody EmailRequest request
    ) {
        log.info("Received email request for {}", request.getTo());

        emailService.sendEmail(request);

        return success("Email request accepted");
    }

    //EMAIL WITH ATTACHMENT
    @Operation(summary = "Send email with attachment using universal template")
    @PostMapping(
            value = "/email/attachment",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ApiResponse sendEmailWithAttachment(
            @RequestPart("emailRequest") String emailRequestJson,
            @RequestPart("file") MultipartFile file
    ) {
        try {
            EmailRequest emailRequest =
                    objectMapper.readValue(
                            emailRequestJson,
                            EmailRequest.class
                    );

            log.info(
                "Received attachment email request for {}, file={}",
                emailRequest.getTo(),
                file.getOriginalFilename()
            );

            AttachmentData attachment = new AttachmentData(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );

            emailService.sendEmailWithAttachment(
                    emailRequest,
                    attachment
            );

            return success("Email with attachment accepted");

        } catch (Exception e) {
            log.error("Invalid attachment email request", e);
            throw new EmailSendException(
                    "Invalid attachment email request",
                    e
            );
        }
    }

    // TEMPLATE EMAIL
    @Operation(summary = "Send template based email (OTP / Offer / Notice)")
    @PostMapping("/email/template")
    public ApiResponse sendTemplateEmail(
            @Valid @RequestBody TemplateEmailRequest request
    ) {
        log.info(
            "Received template email request [{}] for {}",
            request.getTemplateName(),
            request.getTo()
        );

        emailService.sendTemplateEmail(request);

        return success("Template email accepted");
    }

    // RESPONSE
    private ApiResponse success(String msg) {
        return new ApiResponse(
                "SUCCESS",
                msg,
                LocalDateTime.now()
        );
    }
}
