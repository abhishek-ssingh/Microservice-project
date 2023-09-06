package com.acro.departmentservice.repository;

import com.acro.departmentservice.dto.DepartmentDto;
import com.acro.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByDepartmentCode(String departmentCode);
}
