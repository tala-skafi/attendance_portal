package com.attendancePortalBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
// put the same name as the table
@Entity(name="vacation_detail")
public class VacationDetail{

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vacationDetailId;  // Auto-increment
    private String vacationType;
    private Date vacationDateFrom;
    private Date vacationDateTo;
    private String vacationReason;

}
