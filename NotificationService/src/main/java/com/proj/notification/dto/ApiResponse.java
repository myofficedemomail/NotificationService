package com.proj.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiResponse {
	private String status;
	private String message;
	private LocalDateTime timestamp;
}
