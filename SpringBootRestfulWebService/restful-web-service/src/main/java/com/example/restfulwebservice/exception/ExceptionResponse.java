package com.example.restfulwebservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

        public enum ERROR_TYPE {
            SERVER_ERROR,
            NOT_FOUND,
            ARGUMENT_NOT_VALID,
        };

    private Date timeStamp;
    private ERROR_TYPE type;
    private String message;
    private String details;
}
