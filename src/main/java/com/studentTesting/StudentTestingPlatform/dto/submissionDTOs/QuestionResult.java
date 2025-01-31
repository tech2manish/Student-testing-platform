package com.studentTesting.StudentTestingPlatform.dto.submissionDTOs;

public record QuestionResult(
        Integer questionId,
        Integer selectedOptionId,
        boolean isCorrect
){
    
}
