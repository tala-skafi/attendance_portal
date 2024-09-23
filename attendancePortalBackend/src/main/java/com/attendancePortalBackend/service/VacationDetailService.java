package com.attendancePortalBackend.service;

import com.attendancePortalBackend.exception.VacationNotFoundException;
import com.attendancePortalBackend.exception.InvalidVacationDetailException; // Custom exception for validation
import com.attendancePortalBackend.model.VacationDetail;
import com.attendancePortalBackend.repository.VacationDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VacationDetailService {

    @Autowired
    private VacationDetailRepository vacationDetailRepository;

    // Fetch vacation by ID, ensuring it's not deleted (isDeleted = '0')
    public Optional<VacationDetail> getVacationById(Long id) {
        return vacationDetailRepository.findByIdAndIsDeleted(id, "0")
                .or(() -> {
                    throw new VacationNotFoundException(id); // Throw exception if not found
                });
    }

    // Fetch all vacations that are not deleted
    public List<VacationDetail> getAllVacations() {
        return vacationDetailRepository.findAllByIsDeleted("0");
    }

    // Add a new vacation detail with validation
    public VacationDetail addVacation(VacationDetail vacationDetail) {
        validateVacationDetail(vacationDetail); // Perform validation
        return vacationDetailRepository.save(vacationDetail);
    }

    // Update a vacation detail with validation
    public VacationDetail updateVacation(Long id, VacationDetail updatedVacationDetail) {
        vacationDetailRepository.findByIdAndIsDeleted(id, "0")
                .orElseThrow(() -> new VacationNotFoundException(id)); // Throw exception if not found
        validateVacationDetail(updatedVacationDetail); // Perform validation
        updatedVacationDetail.setId(id);
        return vacationDetailRepository.save(updatedVacationDetail);
    }

    // Soft delete a vacation detail by setting isDeleted to '1'
    public void deleteVacation(Long id) {
        VacationDetail vacationDetail = vacationDetailRepository.findByIdAndIsDeleted(id, "0")
                .orElseThrow(() -> new VacationNotFoundException(id)); // Throw exception if not found
        vacationDetail.setIsDeleted("1"); // Soft delete
        vacationDetailRepository.save(vacationDetail); // Save changes
    }

    @Scheduled(cron = "${cron-every-midnight}")
    public void deleteAllVacations() {
        List<VacationDetail> vacationDetails = getAllVacations();
        // Soft delete each vacation
        for (VacationDetail vacationDetail : vacationDetails) {
            vacationDetail.setIsDeleted("1");
        }
        vacationDetailRepository.saveAll(vacationDetails);
    }

    // Validate the vacation detail fields
    private void validateVacationDetail(VacationDetail vacationDetail) {
        if (vacationDetail.getVacationType() == null || vacationDetail.getVacationType().isEmpty()) {
            throw new InvalidVacationDetailException("Vacation type is required.");
        }
        if (vacationDetail.getVacationDateFrom() == null) {
            throw new InvalidVacationDetailException("Vacation start date is required.");
        }
        if (vacationDetail.getVacationDateTo() == null) {
            throw new InvalidVacationDetailException("Vacation end date is required.");
        }
//        if (vacationDetail.getVacationReason() == null || vacationDetail.getVacationReason().isEmpty()) {
//            throw new InvalidVacationDetailException("Vacation reason is required.");
//        }
        // I will add more validations
    }
}
