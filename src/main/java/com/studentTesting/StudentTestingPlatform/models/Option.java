package com.studentTesting.StudentTestingPlatform.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_option")
public class Option {

    @Id
    @GeneratedValue
    private Integer id;

    private String optionText;

    private boolean isCorrect;

    // Mapping with Question Entity
    @ManyToOne
    @JoinColumn(
            name = "question_id"
    )
    @JsonBackReference
    private Question question;

    @OneToMany(mappedBy = "option")
    private List<Answer> answer;


}
