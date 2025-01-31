package com.studentTesting.StudentTestingPlatform.controllers;

import com.studentTesting.StudentTestingPlatform.dto.studentDTOs.StudentRequestDto;
import com.studentTesting.StudentTestingPlatform.dto.studentDTOs.StudentResponseDto;
import com.studentTesting.StudentTestingPlatform.dto.submissionDTOs.SubmissionDetailsDto;
import com.studentTesting.StudentTestingPlatform.models.Student;
import com.studentTesting.StudentTestingPlatform.models.Submission;
import com.studentTesting.StudentTestingPlatform.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //Register teacher, with required credentials
    @PostMapping("/students/register")
    public StudentResponseDto registerStudent(
            @RequestBody StudentRequestDto studentRequestDto
    ){
        return this.studentService.registerStudent(studentRequestDto);
    }

    // get all submissions associated with a student, accessed by student
    @GetMapping("/students/{student_id}/submissions")
    public List<SubmissionDetailsDto> getSubmissionsByStudentId(
            @PathVariable("student_id") Integer studentId
    ){
        return this.studentService.getSubmissionsByStudentId(studentId);
    }
}
