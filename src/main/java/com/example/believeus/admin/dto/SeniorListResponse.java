package com.example.believeus.admin.dto;

import lombok.Builder;

@Builder
public record SeniorListResponse(
    String name,
    String age,
    String gender
) {

}
