package com.example.believeus.common.exception;

import com.example.believeus.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // @Valid 검증 실패 시 400 오류 반환
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        BindingResult bindingResult = ex.getBindingResult();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors); // HTTP 400 Bad Request
    }

    // IllegalArgumentException 발생 시 400 오류 반환
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    // 데이터베이스 제약 조건 위반 (중복 회원가입 등)
    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(org.springframework.dao.DataIntegrityViolationException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "중복된 정보가 존재하거나 잘못된 값이 입력되었습니다.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);       // HTTP 409 Conflict
    }

    // 기타 500 오류에 대한 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "서버 내부 오류가 발생했습니다. 관리자에게 문의하세요.");
        response.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);      // HTTP 500 Internal Server Error
    }
}
