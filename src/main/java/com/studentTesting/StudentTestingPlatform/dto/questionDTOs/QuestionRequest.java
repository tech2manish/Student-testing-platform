package com.studentTesting.StudentTestingPlatform.dto.questionDTOs;

import java.util.List;

public record QuestionRequest(
        String questionText,
        List<OptionRequest> options
) {
}
