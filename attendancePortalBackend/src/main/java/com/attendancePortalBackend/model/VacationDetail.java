package com.attendancePortalBackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
// put the same name as the table
@Entity(name="vacation_detail")
public class VacationDetail extends BaseModel{
    private String vacationType;
    private Date vacationDateFrom;
    private Date vacationDateTo;
    private String vacationReason;

}
