package com.directrice.banking.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Response {

    private String message;
    private Object data;
    private String timeStamp=LocalDateTime.now().toString();

}