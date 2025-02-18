package com.example.believeus.workingcondition.service;

import com.example.believeus.workingcondition.domain.WorkingCondition;
import com.example.believeus.workingcondition.dto.SaveWorkingConditionRequest;
import com.example.believeus.workingcondition.dto.UpdateWorkingConditionRequest;
import com.example.believeus.workingcondition.repository.WorkingConditionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkingConditionService {

    private final WorkingConditionRepository workingConditionRepository;

    public WorkingCondition save(SaveWorkingConditionRequest request) {
        WorkingCondition workingCondition = WorkingCondition.builder()
                .location(request.getLocation())
                .availableTime(request.getAvailableTime())
                .preferredSalary(request.getPreferredSalary())
                .build();
        WorkingCondition save = workingConditionRepository.save(workingCondition);

        return save;
    }

    public WorkingCondition findById(Long id) {
        return workingConditionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
    }

    @Transactional
    public WorkingCondition update(UpdateWorkingConditionRequest request) {
        WorkingCondition workingCondition = workingConditionRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        workingCondition.update(request.getLocation(), request.getAvailableTime(), request.getPreferredSalary());
        return workingCondition;
    }
}
