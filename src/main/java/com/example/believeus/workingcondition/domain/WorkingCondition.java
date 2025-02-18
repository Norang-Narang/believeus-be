package com.example.believeus.workingcondition.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class WorkingCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;

    private String availableTime;

    private Integer preferredSalary;

    @Builder
    public WorkingCondition(String location, String availableTime, Integer preferredSalary) {
        this.location = location;
        this.availableTime = availableTime;
        this.preferredSalary = preferredSalary;
    }

    public void update(String location, String availableTime, Integer preferredSalary) {
        this.location = location;
        this.availableTime = availableTime;
        this.preferredSalary = preferredSalary;
    }
}
