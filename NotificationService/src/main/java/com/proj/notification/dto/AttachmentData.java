package com.proj.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttachmentData {

	private String fileName;
	private String contentType;
	private byte[] data;
}
