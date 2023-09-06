package com.acro.departmentservice.controller;

import com.acro.departmentservice.dto.DepartmentDto;
import com.acro.departmentservice.entity.Department;
import com.acro.departmentservice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Department Service - DepartmentController",
        description = "Department Controller exposes REST APIS"
)

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    //save department api
    @Operation(
            summary = "Save Department REST API",
            description = "Used to save the department info in the db"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<DepartmentDto>(savedDepartment, HttpStatus.OK);
    }

    //get department by departmentcode
    @Operation(
            summary = "Get Department By Code REST API",
            description = "Used to get the department info by code"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 201 SUCCESS"
    )
    @GetMapping("{departmentcode}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable String departmentcode){
        DepartmentDto department = departmentService.getDepartmentByCode(departmentcode);
        return new ResponseEntity<DepartmentDto>(department, HttpStatus.OK);
    }
}
