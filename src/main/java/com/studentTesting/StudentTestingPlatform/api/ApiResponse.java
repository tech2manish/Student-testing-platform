package com.studentTesting.StudentTestingPlatform.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private final String status;
    private final String message;
    private final Object data;
}
