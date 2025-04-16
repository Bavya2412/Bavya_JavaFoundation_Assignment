package com.hexaware.careerhub.util;

import com.hexaware.careerhub.exception.InvalidEmailException;

public class EmailValidator {
    public static void validate(String email) throws InvalidEmailException {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new InvalidEmailException("Invalid email format: " + email);
        }
    }
}
