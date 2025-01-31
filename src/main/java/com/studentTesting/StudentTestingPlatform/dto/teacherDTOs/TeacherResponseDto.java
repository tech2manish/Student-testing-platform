package com.studentTesting.StudentTestingPlatform.dto.teacherDTOs;

public record TeacherResponseDto(
        Integer id,
        String name,
        String email,
        Long mobileNo
) {
}
