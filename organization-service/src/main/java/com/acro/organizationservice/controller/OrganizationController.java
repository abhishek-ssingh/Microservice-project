package com.acro.organizationservice.controller;

import com.acro.organizationservice.dto.OrganizationDto;
import com.acro.organizationservice.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Organization Service - OrganizationController",
        description = "Organization Controller exposes REST APIS"
)

@RestController
@RequestMapping("/organizations")
public class OrganizationController {


    @Autowired
    private OrganizationService organizationService;

    @Operation(
            summary = "Save Organization REST API",
            description = "Used to save the organization info in the db"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto savedOrganizationDto = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<OrganizationDto>(savedOrganizationDto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Organization By Code REST API",
            description = "Used to save the organization info in the db"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{organizationCode}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable  String organizationCode){
        OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);

        return new ResponseEntity<OrganizationDto>(organizationDto, HttpStatus.OK);
    }
}
