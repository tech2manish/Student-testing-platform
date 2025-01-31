package com.studentTesting.StudentTestingPlatform.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Answer {

    @Id
    @GeneratedValue
    private Integer id;

    private boolean isCorrect;

    //Mapping with Option Entity
    @ManyToOne
    @JoinColumn(
            name = "selected_option_id",
            nullable = false
    )
    @JsonBackReference
    private Option option;

    // Mapping with Question Entity
    @ManyToOne
    @JoinColumn(
            name = "question_id",
            nullable = false
    )
    @JsonBackReference
    private Question question;

    @ManyToOne
    @JoinColumn(
            name = "submission_id",
            nullable = false
    )
    @JsonBackReference
    private Submission submission;

}
