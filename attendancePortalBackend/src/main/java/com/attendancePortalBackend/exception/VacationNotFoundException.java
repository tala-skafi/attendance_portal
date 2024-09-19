package com.attendancePortalBackend.exception;

public class VacationNotFoundException extends RuntimeException {
    public VacationNotFoundException(Long id) {
        super("Vacation detail not found with id: " + id);
    }
}