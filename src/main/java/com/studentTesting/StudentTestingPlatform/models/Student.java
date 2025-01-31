package com.studentTesting.StudentTestingPlatform.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String email;

    private Long mobileNo;

    private String password;

    private LocalDateTime createdAt;

    // Mapping with Submission Entity
    @OneToMany(mappedBy = "student")
    private List<Submission> submissions;
}
