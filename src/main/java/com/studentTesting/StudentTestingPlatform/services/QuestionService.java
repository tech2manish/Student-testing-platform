package com.studentTesting.StudentTestingPlatform.services;

import com.studentTesting.StudentTestingPlatform.dto.questionDTOs.OptionRequest;
import com.studentTesting.StudentTestingPlatform.dto.questionDTOs.QuestionRequest;
import com.studentTesting.StudentTestingPlatform.dto.questionDTOs.QuestionResponse;
import com.studentTesting.StudentTestingPlatform.models.Option;
import com.studentTesting.StudentTestingPlatform.models.Question;
import com.studentTesting.StudentTestingPlatform.models.Test;
import com.studentTesting.StudentTestingPlatform.repositories.OptionRepository;
import com.studentTesting.StudentTestingPlatform.repositories.QuestionRepository;
import com.studentTesting.StudentTestingPlatform.repositories.TestRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;
    private final OptionRepository optionRepository;

    public QuestionService(QuestionRepository questionRepository, TestRepository testRepository, OptionRepository optionRepository) {
        this.questionRepository = questionRepository;
        this.testRepository = testRepository;
        this.optionRepository = optionRepository;
    }

    // This section only accessed by the teacher to create a question into the test
    @Transactional
    public QuestionResponse addQuestionToTest(QuestionRequest quesstionRequest, Integer testId){

        // validating is test exists or not
        Test test = testRepository.findById(testId)
                .orElseThrow(()-> new EntityNotFoundException("Test not found with Id "+ testId));

        // Associate all the necessary component with the question
        Question question = new Question();
        question.setTest(test);
        question.setQuestionText(quesstionRequest.questionText());

       // fetching list of options
        List<OptionRequest> optionRequests = quesstionRequest.options();

        // create options and Associate with them question
        List<Option> options = new ArrayList<>();
        for (OptionRequest optionRequest : optionRequests) {
            Option option = new Option();

            // set all the data into the Option
            option.setOptionText(optionRequest.optionText());
            option.setCorrect(optionRequest.isCorrect());
            option.setQuestion(question);

            // adding all the option into the options
            options.add(option);
        }

        // Mapping options with question
        question.setOptions(options);

        // saving question and Options
        optionRepository.saveAll(options);
        questionRepository.save(question);

        String answer = "";
        for (Option option : options) {
            if(option.isCorrect()){
                answer = option.getOptionText();
            }
        }
        return new QuestionResponse(
                question.getQuestionText(),
                answer
        );
    }

    public List<QuestionResponse> getQuestionsByTestId(Integer testId){
        List<Question> questions = questionRepository.findByTestId(testId);
        List<QuestionResponse> questionResponses= new ArrayList<>();
        for (Question question : questions) {
            String answer=null;
            List<Option> options = question.getOptions();
            for (Option option : options) {
                if (option.isCorrect()){
                    answer = option.getOptionText();
                }
            }

            questionResponses.add(new QuestionResponse(question.getQuestionText(),answer));
        }

        return questionResponses;
    }
}
