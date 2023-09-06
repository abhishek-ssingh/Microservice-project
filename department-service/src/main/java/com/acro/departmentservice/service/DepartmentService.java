package com.acro.departmentservice.service;

import com.acro.departmentservice.dto.DepartmentDto;
import com.acro.departmentservice.entity.Department;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentByCode (String departmentCode);
}
