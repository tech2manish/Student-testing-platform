package com.studentTesting.StudentTestingPlatform.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Submission {

    @Id
    @GeneratedValue
    private Integer id;

    private int score;

    private LocalDateTime submittedAt;

    //Mapping with Test Entity
    @ManyToOne
    @JoinColumn(
            name = "test_id"
    )
    @JsonBackReference
    private Test test;

    // Mapping with Answer Entity
    @OneToMany(mappedBy = "submission")
    private List<Answer> answers;

    // Mapping with Student Entity
    @ManyToOne
    @JoinColumn(
            name = "student_id"
    )
    @JsonBackReference
    private Student student;

}
