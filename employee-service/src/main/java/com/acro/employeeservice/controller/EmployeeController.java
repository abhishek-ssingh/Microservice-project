package com.acro.employeeservice.controller;

import com.acro.employeeservice.dto.APIResponseDto;
import com.acro.employeeservice.dto.EmployeeDto;
import com.acro.employeeservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Employee Service - EmployeeController",
        description = "Employee Controller exposes REST APIS"
)
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(
            summary = "Save Employee REST API",
            description = "Used to save the employee info in the db"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<EmployeeDto>(savedEmployeeDto, HttpStatus.CREATED);
    }

//    @GetMapping("{email}")
//    public ResponseEntity<EmployeeDto> getByEmail(@PathVariable String email){
//        EmployeeDto employeeDto = employeeService.getByEmail(email);
//        return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);
//
//    }

    @Operation(
            summary = "Get Employee By Id REST API",
            description = "Used to get the employee by id with respective" +
                    "department and organization info by code"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<APIResponseDto> getById(@PathVariable Long id){
        APIResponseDto apiResponseDto = employeeService.getById(id);
        return new ResponseEntity<APIResponseDto>(apiResponseDto, HttpStatus.OK);

    }
}
