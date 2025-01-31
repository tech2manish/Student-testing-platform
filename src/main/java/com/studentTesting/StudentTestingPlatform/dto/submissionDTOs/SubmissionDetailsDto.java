package com.studentTesting.StudentTestingPlatform.dto.submissionDTOs;

public record SubmissionDetailsDto(
        Integer submissionId,
        Integer testId,
        String testTitle,
        Integer score,
        String teacherName
) {
}
