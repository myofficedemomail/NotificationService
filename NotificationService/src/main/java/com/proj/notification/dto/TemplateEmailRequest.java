package com.proj.notification.dto;

import lombok.Data;

@Data
public class TemplateEmailRequest {

	private String to; // receiver
	private String subject; // email subject
	private String templateName;
	private EmailTemplateVariables variables;
}
