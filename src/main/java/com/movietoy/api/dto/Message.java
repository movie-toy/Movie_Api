package com.movietoy.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {

    private StatusEnum status;
    private String message;
    private Object data;

}
