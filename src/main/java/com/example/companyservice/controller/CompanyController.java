package com.example.companyservice.controller;

import com.example.companyservice.entity.PgCompany;
import com.example.companyservice.model.CompanyDTO;
import com.example.companyservice.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/get-all-companies")
    public ResponseEntity<List<CompanyDTO>> getAll(){
        return new ResponseEntity<> (companyService.getCompanies(), HttpStatus.OK);
    }

    @GetMapping("/exist-by-id/{companyId}")
    public boolean existById(@PathVariable("companyId") Long companyId){
        return companyService.findCompany(companyId);
    }

    @PostMapping("/add-company")
    public ResponseEntity<Long> addUser(@RequestBody CompanyDTO companyDTO){
        return new ResponseEntity<>(companyService.createCompany(companyDTO), HttpStatus.OK);
    }

    @GetMapping("/name-by-id/{id}")
    public String getNameById(@PathVariable("id") Long id){
        return companyService.getCompanyName(id);
    }
}