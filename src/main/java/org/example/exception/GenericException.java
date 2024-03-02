package org.example.exception;

import org.example.entity.User;

public class GenericException extends RuntimeException {
    private GenericException(String message) {
        super(message);
    }

    public static GenericException idNotNull() {
        return new GenericException("Id must be null");
    }

    public static GenericException idShouldNotBeNull() {
        return new GenericException("Id should not be null");
    }

    public static GenericException UsernameExists(String username) {
        return new GenericException(String.format("User with username %s exist", username));
    }

    public static GenericException bookNotAvailable(String title) {
        return new GenericException(String.format("Book with title %s in not available", title));
    }

    public static GenericException requestIdDoesNotExists(Long id) {
        throw new GenericException(String.format("Request whith id : %s does not exist", id));
    }
}
