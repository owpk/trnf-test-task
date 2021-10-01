package org.owpk.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String  res) {
        super("Resource not found: " + res);
    }
}
