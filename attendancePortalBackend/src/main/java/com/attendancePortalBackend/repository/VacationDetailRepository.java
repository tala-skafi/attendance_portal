package com.attendancePortalBackend.repository;

import com.attendancePortalBackend.model.VacationDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VacationDetailRepository extends JpaRepository<VacationDetail, Long> {

    // Find a vacation detail by ID, (isDeleted = '0')
    Optional<VacationDetail> findByIdAndIsDeleted(Long id, String isDeleted);

    // Find all vacation details that are not deleted
    List<VacationDetail> findAllByIsDeleted(String isDeleted);
}
