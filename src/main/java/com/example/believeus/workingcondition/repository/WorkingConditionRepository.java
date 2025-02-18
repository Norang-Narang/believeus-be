package com.example.believeus.workingcondition.repository;

import com.example.believeus.workingcondition.domain.WorkingCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingConditionRepository extends JpaRepository<WorkingCondition, Long> {
}
