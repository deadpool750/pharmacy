package com.example.pharmacy.service.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested drug is not found.
 * Returns a 404 NOT FOUND HTTP status.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DrugNotFoundError extends RuntimeException {

    /**
     * Constructs a new DrugNotFoundError with a default message.
     */
    public DrugNotFoundError() {
        super("drug not found");
    }
}
