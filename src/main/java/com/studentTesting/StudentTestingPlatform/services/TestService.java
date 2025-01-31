package com.studentTesting.StudentTestingPlatform.services;

import com.studentTesting.StudentTestingPlatform.dto.testDetails.CreateTestRequestDto;
import com.studentTesting.StudentTestingPlatform.dto.testDetails.TestDetailsDto;
import com.studentTesting.StudentTestingPlatform.models.Teacher;
import com.studentTesting.StudentTestingPlatform.models.Test;
import com.studentTesting.StudentTestingPlatform.repositories.QuestionRepository;
import com.studentTesting.StudentTestingPlatform.repositories.SubmissionRepository;
import com.studentTesting.StudentTestingPlatform.repositories.TeacherRepository;
import com.studentTesting.StudentTestingPlatform.repositories.TestRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    private final TestRepository testRepository;
    private final TeacherRepository teacherRepository;


    public TestService(TestRepository testRepository, TeacherRepository teacherRepository) {
        this.testRepository = testRepository;
        this.teacherRepository = teacherRepository;
    }

    // create test only accessible by teachers
    public TestDetailsDto createTest(CreateTestRequestDto createTestRequestDto, Integer teacherId){
        // validating is teacher exists or not
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(()-> new EntityNotFoundException("Teacher not found with Id : "+ teacherId));

        Test test = new Test();
        test.setTitle(createTestRequestDto.title());
        test.setDescription(createTestRequestDto.description());
        test.setPassCode(createTestRequestDto.passCode());

        test.setTeacher(teacher);

        Test createdTest = testRepository.save(test);

        return new TestDetailsDto(
                createdTest.getId(),
                createdTest.getTitle(),
                createdTest.getDescription(),
                createdTest.getPassCode()
        );
    }

    public List<Test> getTestsByTeacherId(Integer teacherId){
        return testRepository.findByTeacherId(teacherId);
    }

    // get test by passcode by the student
    public TestDetailsDto findTestByPassCode(String passcode){
        Test test =  testRepository.findByPassCode(passcode)
                .orElseThrow(()-> new EntityNotFoundException("Test not found with passcode : "+ passcode));

        return new TestDetailsDto(
                test.getId(),
                test.getTitle(),
                test.getDescription(),
                test.getPassCode()
        );
    }

    public Test findByTestId(Integer testId) {
        return testRepository.findById(testId)
                .orElseThrow(()-> new EntityNotFoundException("Test Not Found"));
    }
}
