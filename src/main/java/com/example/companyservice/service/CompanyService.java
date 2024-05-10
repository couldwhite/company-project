package com.example.companyservice.service;

import com.example.companyservice.entity.PgCompany;
import com.example.companyservice.feignclient.UserServiceFeignClient;
import com.example.companyservice.model.CompanyDTO;
import com.example.companyservice.repository.PgCompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final PgCompanyRepository pgCompanyRepository;
    private final UserServiceFeignClient userServiceFeignClient;

    public boolean findCompany (Long id){
        PgCompany pgCompany = pgCompanyRepository.getPgCompanyById(id);
        return pgCompany != null;
    }

    public String getCompanyName (Long id){
        PgCompany pgCompany = pgCompanyRepository.getPgCompanyById(id);
        return pgCompany.getName();
    }

    public List<CompanyDTO> getCompanies(){
        List<PgCompany> pgCompanies = pgCompanyRepository.findAll();
        List<CompanyDTO> companyDTOS = new ArrayList<>();
        for (PgCompany pgCompany:pgCompanies) {
            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setId(pgCompany.getId());
            companyDTO.setName(pgCompany.getName());
            companyDTO.setOgrn(pgCompany.getOgrn());
            companyDTO.setDescription(pgCompany.getDescription());
            companyDTO.setDirectorId(pgCompany.getDirectorId());
            String name = userServiceFeignClient.getNameById(pgCompany.getDirectorId());
            companyDTO.setDirectorName(name);
            companyDTOS.add(companyDTO);
        }
        return companyDTOS;
    }

    public Long createCompany(CompanyDTO dto){
        boolean isExist = userServiceFeignClient.existByIdCompany(dto.getDirectorId());
        if(!isExist){
            throw new EntityNotFoundException( "Директора %s не существует".formatted(dto.getDirectorId()));
        }
        return pgCompanyRepository.save(dto.toEntity()).getId();
    }
}
