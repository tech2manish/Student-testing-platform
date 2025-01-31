package com.studentTesting.StudentTestingPlatform.services;

import com.studentTesting.StudentTestingPlatform.dto.teacherDTOs.TeacherRegisterRequestDto;
import com.studentTesting.StudentTestingPlatform.dto.teacherDTOs.TeacherResponseDto;
import com.studentTesting.StudentTestingPlatform.dto.testDetails.TestDetailsDto;
import com.studentTesting.StudentTestingPlatform.exceptions.TeacherException;
import com.studentTesting.StudentTestingPlatform.models.Teacher;
import com.studentTesting.StudentTestingPlatform.models.Test;
import com.studentTesting.StudentTestingPlatform.repositories.TeacherRepository;
import com.studentTesting.StudentTestingPlatform.repositories.TestRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TestRepository testRepository;


    public TeacherService(TeacherRepository teacherRepository, TestRepository testRepository, TestService testService) {
        this.teacherRepository = teacherRepository;
        this.testRepository = testRepository;

    }

    //Register teacher and get the confirmation with ID
    public TeacherResponseDto registerTeacher(TeacherRegisterRequestDto teacherRegisterRequestDto){
        // check teacher already exists
        Teacher teacherExists = teacherRepository.findByEmail(teacherRegisterRequestDto.email());
        if(teacherExists != null){
            throw new TeacherException("Teacher already exists");
        }

        Teacher teacher = new Teacher();
        teacher.setName(teacherRegisterRequestDto.name());
        teacher.setEmail(teacherRegisterRequestDto.email());
        teacher.setMobileNo(teacherRegisterRequestDto.mobileNo());
        teacher.setPassword(teacherRegisterRequestDto.password());

        Teacher savedTeacher = teacherRepository.save(teacher);
        teacher.setId(savedTeacher.getId());
        return new TeacherResponseDto(teacher.getId(),teacher.getName(),teacher.getEmail(),teacher.getMobileNo());
    }

    public Optional<Teacher> findById(Integer teacherId){
        return teacherRepository.findById(teacherId);
    }

    // check teacher valid or not
    public TeacherResponseDto isValidUser(String email, String password){
        Teacher teacher = teacherRepository.findByEmail(email);
        if(teacher == null){
            throw new TeacherException("Teacher not found");
        }
        else if(!teacher.getPassword().equals(password)) {
            throw new TeacherException("invalid credentials");
        }
        return new TeacherResponseDto(teacher.getId(), teacher.getName(),teacher.getEmail(),teacher.getMobileNo());
    }

    // return all tests associated with a teacher
    public List<TestDetailsDto> getTestsByTeacherId(Integer teacherId){
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(()-> new EntityNotFoundException("Teacher not found with Id:"+teacherId));

        List<Test> tests = testRepository.findByTeacherId(teacherId);

        List<TestDetailsDto> testDetailsDtos = new ArrayList<>();
        for (Test test : tests) {
            TestDetailsDto testDetailsDto= new TestDetailsDto(
                    test.getId(),
                    test.getTitle(),
                    test.getDescription(),
                    test.getPassCode()
            );

            testDetailsDtos.add(testDetailsDto);
        }

        return testDetailsDtos;
    }


}
