package com.entradasonline.entradasonline.exception.response;

import lombok.*;

//@JsonRootName("error")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private int code;
}
