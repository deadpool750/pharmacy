package com.example.pharmacy.service.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DrugNotFoundError extends RuntimeException {
    public DrugNotFoundError() {
        super("drug not found");
    }
}
