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
public class Question {

    @Id
    @GeneratedValue
    private Integer id;

    private String questionText;

    private LocalDateTime createdAt;

    // Mapping with Test Entity
    @ManyToOne
    @JoinColumn(
            name = "test_id"
    )
    @JsonBackReference
    private Test test;

    // Mapping with Option Entity
    @OneToMany(mappedBy = "question")
    private List<Option> options;

    // Mapping with Answer Entity
    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

}
