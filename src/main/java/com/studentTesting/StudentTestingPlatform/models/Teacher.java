package com.studentTesting.StudentTestingPlatform.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Teacher {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Column(unique = true)
    private String email;

    private Long mobileNo;

    private String password;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "teacher")
    private List<Test> tests;
}
