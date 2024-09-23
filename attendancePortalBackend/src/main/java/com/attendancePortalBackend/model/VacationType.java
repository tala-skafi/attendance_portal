package com.attendancePortalBackend.model;

import lombok.Getter;

@Getter
public enum VacationType {
    ANNUAL_VACATION(false),
    DEATH_VACATION(false),
    SICK_VACATION(false),
    UNPAID_VACATION(true);

    private final boolean requiresReason;

    VacationType(boolean requiresReason) {
        this.requiresReason = requiresReason;
    }
    public boolean isRequiresReason() {
        return requiresReason;
    }

}
