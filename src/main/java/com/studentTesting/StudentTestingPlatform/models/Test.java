package com.studentTesting.StudentTestingPlatform.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Test {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    private String description;

    private String passCode;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(
            name = "teacher_id"
    )
    @JsonBackReference
    private Teacher teacher;

    // Mapping with Question Entity
    @OneToMany(mappedBy = "test")
    private List<Question> questions;

    // Mapping with Submission Entity
    @OneToMany(mappedBy = "test")
    private List<Submission> submissions;
}
