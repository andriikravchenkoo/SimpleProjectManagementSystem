package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.http.HttpStatus;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {

    private Date timestamp;

    private Integer errorCode;

    private HttpStatus statusCode;

    private String errorMessage;
}
