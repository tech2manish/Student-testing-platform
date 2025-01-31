package com.studentTesting.StudentTestingPlatform.dto.teacherDTOs;

public record TeacherRegisterRequestDto(
        String name,
        String email,
        Long mobileNo,
        String password
) {
}
