package org.almox.core.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class ErrorMessage implements Serializable {
    private String type;
    private int status;
    private Instant timestamp;
    private String message;
    private String method;
    private String uri;
    private Map<String, ? extends Serializable> errors;
}
