package com.example.believeus.workingcondition.controller;

import com.example.believeus.workingcondition.domain.WorkingCondition;
import com.example.believeus.workingcondition.dto.SaveWorkingConditionRequest;
import com.example.believeus.workingcondition.dto.UpdateWorkingConditionRequest;
import com.example.believeus.workingcondition.repository.WorkingConditionRepository;
import com.example.believeus.workingcondition.service.WorkingConditionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/caregivers/work")
public class WorkingConditionController {

    private final WorkingConditionService workingConditionService;
    private final WorkingConditionRepository workingConditionRepository;

    @PostMapping
    public WorkingCondition save(@RequestBody SaveWorkingConditionRequest request) {
        return workingConditionService.save(request);
    }

    @GetMapping("{id}")
    public WorkingCondition find(@PathVariable Long id) {
        return workingConditionService.findById(id);
    }

    @PutMapping
    public WorkingCondition update(@RequestBody UpdateWorkingConditionRequest request) {
        return workingConditionService.update(request);
    }
}
