package com.studentTesting.StudentTestingPlatform.dto.submissionDTOs;

import java.util.List;

public record SubmissionRequest(
        Integer studentId,
        List<AnswerRequest> answers
) {
}

