package com.glady.codinggame.controller;

import com.glady.codinggame.dto.CompanyDto;
import com.glady.codinggame.service.CompanyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {


    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Add a new Company.")
    public ResponseEntity<CompanyDto> addCompany(@RequestBody CompanyDto companyDto)  {
        return new ResponseEntity<>(companyService.create(companyDto), HttpStatus.OK);
    }
}
