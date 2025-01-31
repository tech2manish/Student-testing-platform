package com.studentTesting.StudentTestingPlatform.dto.studentDTOs;

public record StudentRequestDto(
        String name,
        String email,
        Long mobileNo,
        String password
) {
}
