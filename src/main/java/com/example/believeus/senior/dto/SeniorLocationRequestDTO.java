package com.example.believeus.senior.dto;

import com.example.believeus.util.Location;
import lombok.Data;

@Data
public class SeniorLocationRequestDTO {
    private Location location;  // 단일 지역

    public void validate() {
        if (location == null) {
            throw new IllegalArgumentException("지역 정보는 필수입니다.");
        }
    }
}
