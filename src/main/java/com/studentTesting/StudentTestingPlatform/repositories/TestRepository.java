package com.studentTesting.StudentTestingPlatform.repositories;

import com.studentTesting.StudentTestingPlatform.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Integer> {

    List<Test> findByTeacherId(Integer teacherId);

    Optional<Test> findByPassCode(String passCode);
}
