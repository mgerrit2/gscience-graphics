package com.gscience.graphics.gscience_graphics.geom2d.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LineLineException extends Exception {

    private final ErrorCode errorCode;

    public LineLineException(String message,ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}