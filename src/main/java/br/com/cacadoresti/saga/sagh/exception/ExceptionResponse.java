package br.com.cacadoresti.saga.sagh.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private String status;
    private Map<String, List<String>> errors;
    private String path;

    public ExceptionResponse(Exception ex) {
        //...
    }
}
