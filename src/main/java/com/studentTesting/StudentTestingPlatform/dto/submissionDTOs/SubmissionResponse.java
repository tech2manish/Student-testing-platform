package com.studentTesting.StudentTestingPlatform.dto.submissionDTOs;

import java.util.List;

public record SubmissionResponse(
        Integer submissionId,
        Integer testId,
        Integer score,
        List<QuestionResult> questionResults
) {
}

