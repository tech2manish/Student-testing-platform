package com.studentTesting.StudentTestingPlatform.dto;

public record OptionDto(
        Integer id,
        String optionText,
        boolean isCorrect
) {
}
