package com.studentTesting.StudentTestingPlatform.services;

import com.studentTesting.StudentTestingPlatform.dto.studentDTOs.StudentRequestDto;
import com.studentTesting.StudentTestingPlatform.dto.studentDTOs.StudentResponseDto;
import com.studentTesting.StudentTestingPlatform.dto.submissionDTOs.SubmissionDetailsDto;
import com.studentTesting.StudentTestingPlatform.models.Student;
import com.studentTesting.StudentTestingPlatform.models.Submission;
import com.studentTesting.StudentTestingPlatform.repositories.StudentRepository;
import com.studentTesting.StudentTestingPlatform.repositories.SubmissionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final SubmissionRepository submissionRepository;

    public StudentService(StudentRepository studentRepository, SubmissionRepository submissionRepository) {
        this.studentRepository = studentRepository;
        this.submissionRepository = submissionRepository;
    }

    // register student
    public StudentResponseDto registerStudent(StudentRequestDto studentRequestDto){
        Student student = new Student();
        student.setName(studentRequestDto.name());
        student.setEmail(studentRequestDto.email());
        student.setMobileNo(studentRequestDto.mobileNo());
        student.setPassword(studentRequestDto.password());

        Student savedStudent = studentRepository.save(student);

        return new StudentResponseDto(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getEmail(),
                savedStudent.getMobileNo()
        );
    }

    public Student findById(Integer studentId){
        return studentRepository.findById(studentId)
                .orElseThrow(()-> new EntityNotFoundException("Student Not Found"));
    }

    // get list of submissions by students, only accessed by student
    public List<SubmissionDetailsDto> getSubmissionsByStudentId(Integer studentId){
        List<Submission> submissions = submissionRepository.findByStudentId(studentId);

        List<SubmissionDetailsDto> submissionDetailsDtoList = new ArrayList<>();
        for (Submission submission : submissions) {
            SubmissionDetailsDto submissionDetailsDto = new SubmissionDetailsDto(
                    submission.getId(),
                    submission.getTest().getId(),
                    submission.getTest().getTitle(),
                    submission.getScore(),
                    submission.getTest().getTeacher().getName()
            );

            submissionDetailsDtoList.add(submissionDetailsDto);
        }
        return submissionDetailsDtoList;
    }
}
