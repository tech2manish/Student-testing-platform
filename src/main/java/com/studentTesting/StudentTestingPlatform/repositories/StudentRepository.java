package com.studentTesting.StudentTestingPlatform.repositories;

import com.studentTesting.StudentTestingPlatform.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
