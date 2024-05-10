package com.example.companyservice.repository;

import com.example.companyservice.entity.PgCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PgCompanyRepository extends JpaRepository<PgCompany, Long> {

    PgCompany getPgCompanyById(Long id);

}
