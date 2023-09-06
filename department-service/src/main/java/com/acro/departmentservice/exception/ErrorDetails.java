package com.acro.departmentservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    //defined custom message we want acc to client
    private LocalDate timestamp;
    private String message;
    private String path;
    private String errorCode;
}
