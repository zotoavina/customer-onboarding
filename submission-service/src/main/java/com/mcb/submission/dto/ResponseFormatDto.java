package com.mcb.submission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseFormatDto {
    private int code;
    private Object data;
    private String message;
    private LocalDateTime responseDatetime;

    public static <T> ResponseEntity<ResponseFormatDto> buildResponse(T data, HttpStatus status, String message) {
        ResponseFormatDto responseFormatDto = ResponseFormatDto.builder()
                .code(status.value())
                .data(data)
                .message(message)
                .responseDatetime(LocalDateTime.now())
                .build();
        return ResponseEntity.status(status).body(responseFormatDto);
    }

    public static <T> ResponseEntity<ResponseFormatDto> buildResponse(T data, HttpStatus status) {
        return buildResponse(data, status, "");
    }

}
