package com.studentTesting.StudentTestingPlatform.dto.questionDTOs;

public record OptionRequest(
        String optionText,
        boolean isCorrect

) {
}
