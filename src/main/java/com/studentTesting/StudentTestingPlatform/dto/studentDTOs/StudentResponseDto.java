package com.studentTesting.StudentTestingPlatform.dto.studentDTOs;

public record StudentResponseDto(
        Integer studentId,
        String name,
        String email,
        Long mobileNo
) {
}
