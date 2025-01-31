package com.studentTesting.StudentTestingPlatform.controllers;

import com.studentTesting.StudentTestingPlatform.dto.submissionDTOs.SubmissionRequest;
import com.studentTesting.StudentTestingPlatform.dto.submissionDTOs.SubmissionResponse;
import com.studentTesting.StudentTestingPlatform.models.Answer;
import com.studentTesting.StudentTestingPlatform.models.Student;
import com.studentTesting.StudentTestingPlatform.models.Submission;
import com.studentTesting.StudentTestingPlatform.services.StudentService;
import com.studentTesting.StudentTestingPlatform.services.SubmissionService;
import com.studentTesting.StudentTestingPlatform.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubmissionController {

    @Autowired
    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService, TestService testService, StudentService studentService) {
        this.submissionService = submissionService;
    }

    // Only Accessible by student to submit the test
    @PostMapping("/tests/{test_id}/submissions")
    public SubmissionResponse submitTest(
            @PathVariable("test_id") Integer testId,
            @RequestBody SubmissionRequest submissionRequest
            ){
        return this.submissionService.submitTest(submissionRequest, testId);
    }

    // get all the submissions from the students for a test, Accessed only by Teacher
    @GetMapping("/tests/{test-id}/submissions")
    public List<SubmissionResponse> getSubmissionsByTeacher(
            @PathVariable("test-id") Integer testId
    ){
        return this.submissionService.getSubmissionsByTestId(testId);
    }

}
