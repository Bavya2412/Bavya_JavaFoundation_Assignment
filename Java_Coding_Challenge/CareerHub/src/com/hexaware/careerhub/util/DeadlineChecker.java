package com.hexaware.careerhub.util;

import com.hexaware.careerhub.exception.ApplicationDeadlineException;

import java.time.LocalDateTime;

public class DeadlineChecker {

    public static void checkDeadline(LocalDateTime deadline) throws ApplicationDeadlineException {
        if (LocalDateTime.now().isAfter(deadline)) {
            throw new ApplicationDeadlineException("The application deadline has already passed.");
        }
    }
}
