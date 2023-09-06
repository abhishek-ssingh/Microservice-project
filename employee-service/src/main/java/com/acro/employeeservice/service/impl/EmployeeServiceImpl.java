package com.acro.employeeservice.service.impl;

import com.acro.employeeservice.dto.APIResponseDto;
import com.acro.employeeservice.dto.DepartmentDto;
import com.acro.employeeservice.dto.EmployeeDto;
import com.acro.employeeservice.dto.OrganizationDto;
import com.acro.employeeservice.entity.Employee;
import com.acro.employeeservice.exception.ResourceNotFoundException;
import com.acro.employeeservice.repository.EmployeeRepository;
import com.acro.employeeservice.service.APIClient;
import com.acro.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;
//    private APIClient apiClient;
    private WebClient webClient;
    private ModelMapper modelMapper;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);

        Employee savedEmployee = employeeRepository.save(employee);
        //maps the employee to dto
        EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);

        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getByEmail(String email) {
        Employee getEmployee = employeeRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "email", email)
        );

        EmployeeDto employeeDto = modelMapper.map(getEmployee, EmployeeDto.class);

        return employeeDto;
    }

//    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
//    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponseDto getById(Long id) {

        Employee getEmployee = employeeRepository.findById(id).get();
        EmployeeDto employeeDto = modelMapper.map(getEmployee, EmployeeDto.class);

        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/departments/" + getEmployee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();
//        DepartmentDto departmentDto = apiClient.getDepartmentByCode(getEmployee.getDepartmentCode());

        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8081/organizations/" + getEmployee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);

        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long id, Exception exception){
        Employee getEmployee = employeeRepository.findById(id).get();
        DepartmentDto departmentDto = new DepartmentDto();

        departmentDto.setDepartmentName("Default");
        departmentDto.setDepartmentCode("X01");
        departmentDto.setDepartmentDescription("Default description for the department");

        EmployeeDto employeeDto = modelMapper.map(getEmployee, EmployeeDto.class);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}
