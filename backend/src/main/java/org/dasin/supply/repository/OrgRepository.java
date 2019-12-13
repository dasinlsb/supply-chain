package org.dasin.supply.repository;

import org.dasin.supply.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrgRepository extends JpaRepository<Organization, String> {
    List<Organization> findByUsernameAndPassword(String username, String password);
    List<Organization> findByUsername(String username);
}
