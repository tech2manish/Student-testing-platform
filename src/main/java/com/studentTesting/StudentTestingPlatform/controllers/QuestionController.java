package com.studentTesting.StudentTestingPlatform.controllers;

import com.studentTesting.StudentTestingPlatform.dto.questionDTOs.OptionRequest;
import com.studentTesting.StudentTestingPlatform.dto.questionDTOs.QuestionRequest;
import com.studentTesting.StudentTestingPlatform.dto.questionDTOs.QuestionResponse;
import com.studentTesting.StudentTestingPlatform.models.Question;
import com.studentTesting.StudentTestingPlatform.models.Test;
import com.studentTesting.StudentTestingPlatform.services.QuestionService;
import com.studentTesting.StudentTestingPlatform.services.TestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // This section only accessed by the teacher to create a question into the test
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/tests/{test_id}/questions")
    public QuestionResponse addQuestionToTest(
            @RequestBody QuestionRequest questionRequest,
            @PathVariable("test_id") Integer testId
    ){
        return this.questionService.addQuestionToTest(questionRequest,testId);
    }

    // This section provide all the questions associated to a question
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/tests/{test_id}/questions")
    public List<QuestionResponse> getQuestionsByTestId(
            @PathVariable("test_id") Integer testId
    ){
        return this.questionService.getQuestionsByTestId(testId);
    }
}
