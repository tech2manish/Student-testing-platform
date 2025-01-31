package com.studentTesting.StudentTestingPlatform.repositories;

import com.studentTesting.StudentTestingPlatform.models.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission,Integer> {

    List<Submission> findByTestId(Integer testId);

    List<Submission> findByStudentId(Integer studentId);
}
