package com.example.companyservice.model;

import com.example.companyservice.entity.PgCompany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompanyDTO {
    private Long id;
    private String name;
    private String ogrn;
    private String description;
    private Long directorId;
    private String directorName;

    public PgCompany toEntity(){
        PgCompany company = new PgCompany();
        company.setName(this.getName());
        company.setOgrn(this.getOgrn());
        company.setDescription(this.getDescription());
        company.setDirectorId(this.getDirectorId());
        return company;
    }
}
