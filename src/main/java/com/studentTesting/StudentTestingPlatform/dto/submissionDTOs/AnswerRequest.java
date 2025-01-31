package com.studentTesting.StudentTestingPlatform.dto.submissionDTOs;

public record AnswerRequest(
        Integer questionId,
        Integer selected_option_id
){}
