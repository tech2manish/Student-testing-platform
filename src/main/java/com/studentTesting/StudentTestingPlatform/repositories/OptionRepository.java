package com.studentTesting.StudentTestingPlatform.repositories;

import com.studentTesting.StudentTestingPlatform.dto.OptionDto;
import com.studentTesting.StudentTestingPlatform.models.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Integer> {
    List<OptionDto> findByQuestionId(Integer questionId);
}
