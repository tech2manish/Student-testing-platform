package com.studentTesting.StudentTestingPlatform.dto.testDetails;

public record TestDetailsDto(
        Integer testId,
        String title,
        String description,
        String passCode
) {
}
