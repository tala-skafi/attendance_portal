package com.attendancePortalBackend.controller;

import com.attendancePortalBackend.exception.VacationNotFoundException;
import com.attendancePortalBackend.model.VacationType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import com.attendancePortalBackend.model.VacationDetail;
import com.attendancePortalBackend.service.VacationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.attendancePortalBackend.configuration.ApplicationConstants;

import java.util.*;
import java.util.stream.Collectors;


@CrossOrigin(origins = ApplicationConstants.CROSS_ORIGIN)
@RestController
@RequestMapping(ApplicationConstants.URL)
public class VacationDetailController {

    @Autowired
    private VacationDetailService vacationDetailService;

    // GET all vacation details
    @GetMapping
    public List<VacationDetail> getAllVacations() {
        return vacationDetailService.getAllVacations();
    }

    // GET vacation types
    @GetMapping(ApplicationConstants.VACATION_TYPES)  // Add this endpoint
    public List<Map<String, Object>> getVacationTypes() {
        return Arrays.stream(VacationType.values())
                .map(type -> {
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("type", type.name()); // Add type first
                    map.put("requiresReason", type.isRequiresReason());
                    return map;
                })
                .collect(Collectors.toList());
    }

    // GET vacation by ID
    @GetMapping(ApplicationConstants.BY_ID)
    public ResponseEntity<VacationDetail> getVacationById(@PathVariable Long id) {
        Optional<VacationDetail> vacationDetail = vacationDetailService.getVacationById(id);
        return vacationDetail.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // POST (Create) a new vacation detail
    @PostMapping
    public List<VacationDetail> createVacation(@RequestBody VacationDetail vacationDetail) {
        // Add the new vacation detail
        vacationDetailService.addVacation(vacationDetail);

        // Return the list of all vacation details
        return vacationDetailService.getAllVacations();
    }

    // PUT (Update) a vacation detail
    @PutMapping(ApplicationConstants.BY_ID)
    public ResponseEntity<VacationDetail> updateVacation(@PathVariable Long id, @RequestBody VacationDetail vacationDetail) {
        VacationDetail updatedVacation = vacationDetailService.updateVacation(id, vacationDetail);
        return ResponseEntity.ok(updatedVacation);
    }

    // DELETE a vacation detail
    @DeleteMapping(ApplicationConstants.BY_ID)
    public ResponseEntity<Void> deleteVacation(@PathVariable Long id) {
        vacationDetailService.deleteVacation(id);
        return ResponseEntity.noContent().build();
    }
}
