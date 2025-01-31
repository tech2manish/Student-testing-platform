package com.studentTesting.StudentTestingPlatform.controllers;

import com.studentTesting.StudentTestingPlatform.dto.testDetails.CreateTestRequestDto;
import com.studentTesting.StudentTestingPlatform.dto.testDetails.TestDetailsDto;
import com.studentTesting.StudentTestingPlatform.models.Test;
import com.studentTesting.StudentTestingPlatform.services.TestService;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    // create test, Only Accessible by Teacher
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/tests/{teacher_id}")
    public TestDetailsDto createTest(
            @RequestBody CreateTestRequestDto createTestRequestDto,
            @PathVariable("teacher_id") Integer teacherId
    ){
        return this.testService.createTest(createTestRequestDto, teacherId);
    }

    // get test by the passcode , Only Accessible by Student
    @GetMapping("/tests/{test_access_key}")
    public TestDetailsDto findTestByPassCode(
            @PathVariable("test_access_key") String passcode
    ){
        return this.testService.findTestByPassCode(passcode);
    }


}
