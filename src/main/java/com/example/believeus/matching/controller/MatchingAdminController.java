package com.example.believeus.matching.controller;

import com.example.believeus.common.ApiResponse;
import com.example.believeus.matching.dto.MatchingResponseDTO;
import com.example.believeus.matching.application.MatchingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/matching")
@RequiredArgsConstructor
public class MatchingAdminController {
    private final MatchingService matchingService;

    @Operation(summary = "매칭 추천 리스트 조회", description = "특정 노인에 대한 추천 요양보호사 리스트를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "매칭 추천 리스트 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "해당 노인을 찾을 수 없음"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/{seniorId}")
    public ResponseEntity<ApiResponse<List<MatchingResponseDTO>>> getMatchingCaregivers(@PathVariable Long seniorId) {
        List<MatchingResponseDTO> matchingList = matchingService.getMatchingCaregivers(seniorId);
        return ResponseEntity.ok(ApiResponse.success(matchingList, "매칭 추천 리스트 조회 성공"));
    }
}
