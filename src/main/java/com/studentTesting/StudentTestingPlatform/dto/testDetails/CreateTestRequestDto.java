package com.studentTesting.StudentTestingPlatform.dto.testDetails;

public record CreateTestRequestDto(
        String title,
        String description,
        String passCode
) {
}
