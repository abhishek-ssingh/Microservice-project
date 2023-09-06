package com.acro.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Schema(
        description = "Employee DTO model"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;
    @Schema(
            description = "Employee First Name"
    )
    private String firstName;
    @Schema(
            description = "Employee Last Name"
    )
    private String lastName;
    @Schema(
            description = "Employee Email"
    )
    private String email;
    @Schema(
            description = "Department Code"
    )
    private String departmentCode;
    @Schema(
            description = "Organization Code"
    )
    private String organizationCode;
}
