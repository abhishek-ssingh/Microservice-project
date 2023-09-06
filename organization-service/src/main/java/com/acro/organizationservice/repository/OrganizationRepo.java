package com.acro.organizationservice.repository;

import com.acro.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepo extends JpaRepository<Organization, Long> {
    Organization findByOrganizationCode(String organizationCode);
}
