package com.studentTesting.StudentTestingPlatform.services;

import com.studentTesting.StudentTestingPlatform.dto.OptionDto;
import com.studentTesting.StudentTestingPlatform.dto.mappers.SubmissionMapper;
import com.studentTesting.StudentTestingPlatform.dto.submissionDTOs.AnswerRequest;
import com.studentTesting.StudentTestingPlatform.dto.submissionDTOs.SubmissionRequest;
import com.studentTesting.StudentTestingPlatform.dto.submissionDTOs.SubmissionResponse;
import com.studentTesting.StudentTestingPlatform.models.*;
import com.studentTesting.StudentTestingPlatform.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final AnswerRepository answerRepository;
    private final TestRepository testRepository;
    private final StudentRepository studentRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final SubmissionMapper submissionMapper;

    public SubmissionService(SubmissionRepository submissionRepository, AnswerRepository answerRepository, TestRepository testRepository, StudentRepository studentRepository, QuestionRepository questionRepository, OptionRepository optionRepository, SubmissionMapper submissionMapper) {
        this.submissionRepository = submissionRepository;
        this.answerRepository = answerRepository;
        this.testRepository = testRepository;
        this.studentRepository = studentRepository;
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
        this.submissionMapper = submissionMapper;
    }

    @Transactional
    public SubmissionResponse submitTest(SubmissionRequest submissionRequest, Integer testId){

        // validating test exists or not
        Test test = testRepository.findById(testId)
                .orElseThrow(()-> new EntityNotFoundException("The Test Not Found with testId: "+ testId));

        // validating student exists or not
        Student student = studentRepository.findById(submissionRequest.studentId())
                .orElseThrow(()-> new EntityNotFoundException("Student not found with student id: "+ submissionRequest.studentId()));

        // fetching list of answers
        List<AnswerRequest> answerRequests = submissionRequest.answers();


         // calculating score for the submission
        int score = 0;
        for (AnswerRequest answerRequest : answerRequests) {
            List<OptionDto> options = optionRepository.findByQuestionId(answerRequest.questionId());
            for (OptionDto option : options) {
                if(option.id().equals(answerRequest.selected_option_id()) && option.isCorrect()){
                    score = score + 1;
                }
            }
        }

        // Mapping all the necessary component to submission
        Submission submission = new Submission();
        submission.setTest(test);
        submission.setStudent(student);
        submission.setScore(score);

        // creating answers and Associate with them submission
        List<Answer> answers = new ArrayList<>();
        for (AnswerRequest answerRequest : answerRequests) {
            Answer answer = new Answer();

            Question question = questionRepository.findById(answerRequest.questionId())
                    .orElseThrow(()-> new EntityNotFoundException("Question Not Found"));

            Option selectedOption = optionRepository.findById(answerRequest.selected_option_id())
                    .orElseThrow(()-> new EntityNotFoundException("Option Not Found with Id: "+ answerRequest.selected_option_id()));


            answer.setQuestion(question);
            answer.setOption(selectedOption);
            answer.setSubmission(submission);
            answer.setCorrect(selectedOption.isCorrect());

            // adding answer to list of answers
            answers.add(answer);
        }

        //make relation with answers and submission
        submission.setAnswers(answers);

        submissionRepository.save(submission);
        answerRepository.saveAll(answers);

        return new SubmissionResponse(submission.getId(), test.getId(),submission.getScore(),null);
    }



    // get all the submissions from the students for a test, Accessed only by Teacher
    @Transactional
    public List<SubmissionResponse> getSubmissionsByTestId(Integer testId){

        // Validating is test exists or not
        Test test = testRepository.findById(testId)
                .orElseThrow(()-> new EntityNotFoundException("Test Not Found"));

        List<Submission> submissions = submissionRepository.findByTestId(testId);

        List<SubmissionResponse> submissionResponseList = new ArrayList<>();
        for (Submission submission : submissions) {
            submissionResponseList.add(submissionMapper.toSubmissionResponseDto(submission));
        }

        // fetching and returning the data
        return submissionResponseList;
    }



    @Transactional
    public List<Submission> getSubmissionsByStudentId(Integer studentId){

        // Validating is Student exists or not
        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new EntityNotFoundException("Student Not Found"));

        //fetching and returning the data
        return submissionRepository.findByStudentId(studentId);
    }
}
