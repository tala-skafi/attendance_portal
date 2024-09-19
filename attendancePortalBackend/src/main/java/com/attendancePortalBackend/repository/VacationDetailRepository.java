package com.attendancePortalBackend.repository;

import com.attendancePortalBackend.model.VacationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationDetailRepository extends JpaRepository<VacationDetail, Long> {
}
