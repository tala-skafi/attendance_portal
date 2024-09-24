package com.attendancePortalBackend.scheduler;

import com.attendancePortalBackend.model.VacationDetail;
import com.attendancePortalBackend.repository.VacationDetailRepository;
import com.attendancePortalBackend.service.VacationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteVacationsCron {
    @Autowired
    private VacationDetailRepository vacationDetailRepository;
    @Autowired
    private VacationDetailService vacationDetailService;


    @Scheduled(cron = "${cron-every-year}")
    public void deleteAllVacations() {
        List<VacationDetail> vacationDetails = vacationDetailService.getAllVacations();
        // Soft delete each vacation
        for (VacationDetail vacationDetail : vacationDetails) {
            vacationDetail.setIsDeleted("1");
        }
        vacationDetailRepository.saveAll(vacationDetails);
    }
}
