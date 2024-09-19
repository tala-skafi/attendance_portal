package com.attendancePortalBackend.service;


import com.attendancePortalBackend.model.VacationDetail;
import com.attendancePortalBackend.repository.VacationDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacationDetailService {

    @Autowired
    private VacationDetailRepository vacationDetailRepository;

    public List<VacationDetail> getAllVacations() {
        return vacationDetailRepository.findAll();
    }

    public Optional<VacationDetail> getVacationById(Long id) {
        return vacationDetailRepository.findById(id);
    }

    public VacationDetail addVacation(VacationDetail vacationDetail) {
        return vacationDetailRepository.save(vacationDetail);
    }

    public void deleteVacation(Long id) {
        vacationDetailRepository.deleteById(id);
    }
    //make some validation like id through exceptions, valid any required field. i will through an exc to be printed to user.
    public VacationDetail updateVacation(Long id, VacationDetail updatedVacationDetail) {
        updatedVacationDetail.setVacationDetailId(id);
        return vacationDetailRepository.save(updatedVacationDetail);
    }
}
