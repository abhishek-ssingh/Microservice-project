package com.acro.organizationservice.service.impl;

import com.acro.organizationservice.dto.OrganizationDto;
import com.acro.organizationservice.entity.Organization;
import com.acro.organizationservice.repository.OrganizationRepo;
import com.acro.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {


    @Autowired
    private OrganizationRepo organizationRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

        Organization organization = modelMapper.map(organizationDto, Organization.class);
        Organization savedOrganization  = organizationRepo.save(organization);
        OrganizationDto savedOrganizationDto = modelMapper.map(savedOrganization, OrganizationDto.class);

        return savedOrganizationDto;
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepo.findByOrganizationCode(organizationCode);

        OrganizationDto organizationDto = modelMapper.map(organization, OrganizationDto.class);

        return organizationDto;
    }
}
