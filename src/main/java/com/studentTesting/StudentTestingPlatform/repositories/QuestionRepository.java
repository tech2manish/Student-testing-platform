package com.studentTesting.StudentTestingPlatform.repositories;

import com.studentTesting.StudentTestingPlatform.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findByTestId(Integer testId);
}
