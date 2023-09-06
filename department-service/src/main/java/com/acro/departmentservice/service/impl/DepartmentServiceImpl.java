package com.acro.departmentservice.service.impl;

import com.acro.departmentservice.dto.DepartmentDto;
import com.acro.departmentservice.entity.Department;
import com.acro.departmentservice.exception.ResourceNotFoundException;
import com.acro.departmentservice.repository.DepartmentRepository;
import com.acro.departmentservice.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    //instead of using autowired we can use constructor of department
    //repo
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        //have to convert dto into entity(department)
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );

        Department savedDepartment = departmentRepository.save(department);

        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department getDepartment = departmentRepository.findByDepartmentCode(departmentCode).
                orElseThrow(
                () -> new ResourceNotFoundException("Department", "Code", departmentCode)
        );
        return  modelMapper.map(getDepartment, DepartmentDto.class);
    }
}
