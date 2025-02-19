package com.example.believeus.caregiver.dto;

import com.example.believeus.util.Location;
import lombok.Data;
import java.util.List;

@Data
public class CaregiverLocationRequestDTO {
    private List<Location> locations; // 최대 3개

    public void validate() {
        if (locations == null || locations.isEmpty()) {
            throw new IllegalArgumentException("적어도 하나의 지역을 등록해야 합니다.");
        }
        if (locations.size() > 3) {
            throw new IllegalArgumentException("최대 3개의 지역만 등록할 수 있습니다.");
        }
    }
}
