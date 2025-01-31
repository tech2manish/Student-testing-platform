package com.studentTesting.StudentTestingPlatform.dto.mappers;

import com.studentTesting.StudentTestingPlatform.dto.submissionDTOs.QuestionResult;
import com.studentTesting.StudentTestingPlatform.models.Answer;
import org.springframework.stereotype.Service;

@Service
public class AnswerMapper {

    public QuestionResult toQuestionResultDto(Answer answer){
        return new QuestionResult(
                answer.getQuestion().getId(),
                answer.getOption().getId(),
                answer.isCorrect()
        );
    }
}
