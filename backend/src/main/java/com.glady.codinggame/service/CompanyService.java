package com.glady.codinggame.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Optional;

import javax.transaction.Transactional;

import com.glady.codinggame.dto.CompanyDto;
import com.glady.codinggame.exception.GladyException;
import com.glady.codinggame.exception.CompanyNotFoundException;
import com.glady.codinggame.exception.InsufficientCashException;
import com.glady.codinggame.repository.CompanyRepository;
import com.glady.codinggame.repository.entity.CompanyEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;


    public CompanyDto create(CompanyDto companyDto) {
        CompanyEntity entity = new CompanyEntity(companyDto.getId(), companyDto.getName(), companyDto.getGiftCash(), companyDto.getMealCash());

        CompanyDto created = Optional.of(entity)
                .map(this.companyRepository::saveAndFlush)
                .map(CompanyService::toCompany)
                .orElseThrow();

        log.info("Company created : "+created);

        return created;
    }

    public CompanyDto getCompanyById(Long companyId) throws CompanyNotFoundException {
        return this.companyRepository.findById(companyId)
                .map(CompanyService::toCompany)
                .orElseThrow(() -> new CompanyNotFoundException());
    }

    public  BigDecimal distributeGiftCash(Long companyId, BigDecimal amount) throws GladyException {
        CompanyEntity company = this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException());

        if (!this.hasEnoughGiftCash(companyId, amount)) {
            throw new InsufficientCashException();
        }

        BigDecimal newCash = company.getGiftCash().subtract(amount);
        company.setGiftCash(newCash);

        this.companyRepository.saveAndFlush(company);

        log.info("withdraw Gift Cash > New Balance"+ newCash);

        return newCash;
    }

    public  BigDecimal distributeMealCash(Long companyId, BigDecimal amount) throws GladyException {
        CompanyEntity company = this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException());

        if (!this.hasEnoughMealCash(companyId, amount)) {
            throw new InsufficientCashException();
        }

        BigDecimal newCash = company.getGiftCash().subtract(amount);
        company.setMealCash(newCash);
        this.companyRepository.saveAndFlush(company);

        log.info("withdraw Meal Cash > New Balance"+ newCash);

        return newCash;
    }

    public boolean hasEnoughGiftCash( Long companyId,  BigDecimal amount) {
        CompanyDto companyDto = this.getCompanyById(companyId);

        return 0 <= companyDto.getGiftCash().compareTo(amount);
    }

    public boolean hasEnoughMealCash(Long companyId, BigDecimal amount) {
        CompanyDto companyDto = this.getCompanyById(companyId);

        return 0 <= companyDto.getMealCash().compareTo(amount);
    }


    private static CompanyDto toCompany(CompanyEntity entity) {
        return CompanyDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .giftCash(entity.getGiftCash())
                .mealCash(entity.getMealCash())
                .build();
    }

}
