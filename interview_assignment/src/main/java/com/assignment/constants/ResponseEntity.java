package com.assignment.constants;

import lombok.Data;

@Data
public class ResponseEntity {
	private String message;
	private Integer status;
	private Object object;
	private String path;
}
