package com.studentTesting.StudentTestingPlatform.services;

import com.studentTesting.StudentTestingPlatform.dto.mappers.AnswerMapper;
import com.studentTesting.StudentTestingPlatform.dto.submissionDTOs.QuestionResult;
import com.studentTesting.StudentTestingPlatform.models.Answer;
import com.studentTesting.StudentTestingPlatform.models.Submission;
import com.studentTesting.StudentTestingPlatform.repositories.AnswerRepository;
import com.studentTesting.StudentTestingPlatform.repositories.SubmissionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final SubmissionRepository submissionRepository;
    private final AnswerMapper answerMapper;

    public AnswerService(AnswerRepository answerRepository, SubmissionRepository submissionRepository, AnswerMapper answerMapper) {
        this.answerRepository = answerRepository;
        this.submissionRepository = submissionRepository;
        this.answerMapper = answerMapper;
    }

    public List<QuestionResult> getAnswersBySubmissionId(Integer submissionId){

        // Validating is Submission Exists or not
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(()-> new EntityNotFoundException("Submission Not Found"));

        List<Answer> answers = submission.getAnswers();

        // create QuestionResults and adding QuestionResult to them
        List<QuestionResult> questionResults = new ArrayList<>();
        for (Answer answer : answers) {
            questionResults.add(answerMapper.toQuestionResultDto(answer));
        }

        //fetching and returning the data
        return questionResults;
    }
}
