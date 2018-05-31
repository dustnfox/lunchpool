package com.dustnfox.lunchpool.util.exception;

import javax.persistence.EntityNotFoundException;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String s, EntityNotFoundException e) {
        super(s, e);
    }
}
