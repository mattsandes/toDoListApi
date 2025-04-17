package org.com.sandes.exceptions;

import java.util.Date;

public class ExceptionResponse {

    private Date timestamp;
    private String description;
    private String details;

    public ExceptionResponse() {
    }

    public ExceptionResponse(Date timestamp, String description, String details) {
        this.timestamp = timestamp;
        this.description = description;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public String getDetails() {
        return details;
    }
}
