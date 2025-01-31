package com.studentTesting.StudentTestingPlatform.dto.mappers;

import com.studentTesting.StudentTestingPlatform.dto.submissionDTOs.SubmissionResponse;
import com.studentTesting.StudentTestingPlatform.models.Submission;
import com.studentTesting.StudentTestingPlatform.services.AnswerService;
import org.springframework.stereotype.Service;

@Service
public class SubmissionMapper {

    private final AnswerService answerService;

    public SubmissionMapper(AnswerService answerService) {
        this.answerService = answerService;
    }

    public SubmissionResponse toSubmissionResponseDto(Submission submission){
        return new SubmissionResponse(
                submission.getId(),
                submission.getTest().getId(),
                submission.getScore(),
                answerService.getAnswersBySubmissionId(submission.getId())
        );
    }
}
