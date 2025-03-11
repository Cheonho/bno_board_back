package com.bno.board_back.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.Locale;

@Hidden
@RestControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class Error {
        private String type;        // 오류를 분류하는 URI 식별자
        private String title;       // 오류에 대한 간략하고 사람이 읽을 수 있는 메시지
        private Integer status;     // HTTP 응답 코드(선택 사항)
        private Object detail;      // 사람이 읽을 수 있는 오류 설명
        private String instance;    // 오류의 특정 발생을 식별하는 URI
        @Builder.Default
        private Instant timestamp = Instant.now();
    }

    @ExceptionHandler({FileException.class})
    public ResponseEntity<Object> handleFileException(FileException ex) {
        final Error error = Error.builder()
                .type("BadRequest")
                .title(messageSource.getMessage("error.file.title", null, Locale.getDefault()))
                .status(HttpStatus.BAD_REQUEST.value())
                .detail(ex.getErrorData())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<Object> handleCustomException(CustomException ex) {
        HttpStatus status = ex.getStatus();
        final Error error = Error.builder()
                .type(ex.getErrorName())
                .title(messageSource.getMessage("error.custom.title", null, Locale.getDefault()))
                .status(status.value())
                .detail(ex.getErrorData())
                .build();

        return ResponseEntity.status(status).body(error);
    }
}
