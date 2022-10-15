package com.glady.codinggame.service;

import com.glady.codinggame.dto.GiftDepositDto;
import com.glady.codinggame.dto.MealDepositDto;
import com.glady.codinggame.exception.CompanyNotFoundException;
import com.glady.codinggame.exception.IllegalAmountException;
import com.glady.codinggame.exception.UserNotFoundException;
import com.glady.codinggame.repository.GiftDepositRepository;
import com.glady.codinggame.repository.MealDepositRepository;
import com.glady.codinggame.repository.entity.GiftDepositEntity;
import com.glady.codinggame.repository.entity.MealDepositEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DepositDistributionService {

    private final CompanyService companyService;
    private final EmployeeService employeeService;
    private final GiftDepositRepository giftDepositRepository;
    private final MealDepositRepository mealDepositRepository;


    public GiftDepositDto distributeGiftDeposits(GiftDepositDto giftDepositDto) throws IllegalAmountException,CompanyNotFoundException,UserNotFoundException {
        Long companyId = giftDepositDto.getCompanyId();
        BigDecimal amount = giftDepositDto.getAmount();
        Long employeeId = giftDepositDto.getEmployeeId();

        this.preconditionsCheck(companyId, amount, employeeId);

        this.companyService.distributeGiftCash(companyId, amount);

        LocalDate expirationDate = getGiftExpirationDate(LocalDate.now());

        GiftDepositDto deposit = new GiftDepositDto(companyId, employeeId, amount, LocalDate.now(), expirationDate);

        deposit = this.createGiftDeposits(deposit);

        return deposit;
    }

    public MealDepositDto distributeMealDeposits(MealDepositDto mealDepositDto) throws IllegalAmountException,CompanyNotFoundException,UserNotFoundException {

        Long companyId = mealDepositDto.getCompanyId();
        BigDecimal amount = mealDepositDto.getAmount();
        Long employeeId = mealDepositDto.getEmployeeId();

        this.preconditionsCheck(companyId, amount, employeeId);

        this.companyService.distributeMealCash(companyId, amount);

        LocalDate date = LocalDate.now();
        LocalDate expirationDate = getMealExpirationDate(date);

        MealDepositDto deposit =  new MealDepositDto(companyId, employeeId, amount, date, expirationDate);

        return this.createMealDeposits(deposit);
    }

    public GiftDepositDto createGiftDeposits(GiftDepositDto giftDeposits) {
        GiftDepositEntity entitie = new GiftDepositEntity(giftDeposits.getCompanyId(),
                giftDeposits.getEmployeeId(),
                giftDeposits.getAmount(),
                giftDeposits.getDepositDate(),
                giftDeposits.getExpirationDate());

        GiftDepositDto deposit =toGiftDeposit( this.giftDepositRepository.saveAndFlush(entitie));

        log.info("Gift Card received with an amount of : "+deposit.getAmount());

        return deposit;
    }

    public MealDepositDto createMealDeposits(MealDepositDto mealDeposit) {
        MealDepositEntity entitie = new MealDepositEntity(mealDeposit.getCompanyId(),
                mealDeposit.getEmployeeId(),
                mealDeposit.getAmount(),
                mealDeposit.getDepositDate(),
                mealDeposit.getExpirationDate());

        MealDepositDto deposit = toMealDeposit(this.mealDepositRepository.saveAndFlush(entitie));

        log.info("Meal Card received with an amount of : "+ deposit.getAmount());

        return deposit;
    }

    public LocalDate getGiftExpirationDate(LocalDate giftDepositDate) {
        return giftDepositDate.plusDays(364);
    }

    public  LocalDate getMealExpirationDate( LocalDate mealDepositDate) {
        return LocalDate.of(mealDepositDate.getYear() + 1, Month.FEBRUARY, 1).with(TemporalAdjusters.lastDayOfMonth());
    }

    private void throwIfUserDoesNotExist( Long employeeIds) throws UserNotFoundException {
        if (!this.employeeService.existsById(employeeIds)) {
            throw new UserNotFoundException();
        }
    }

    private void throwIfAmountIsInvalid( BigDecimal amount) throws IllegalAmountException {
        if (0 <= BigDecimal.ZERO.compareTo(amount)) {
            throw new IllegalAmountException();
        }
    }

    private void preconditionsCheck(Long companyId, BigDecimal amount, Long employeeId) throws IllegalAmountException, CompanyNotFoundException, UserNotFoundException {
        this.throwIfAmountIsInvalid(amount);
        this.companyService.getCompanyById(companyId);
        this.throwIfUserDoesNotExist(employeeId);
    }

    //TODO : enhancement using mapstruct
    public static GiftDepositDto toGiftDeposit(GiftDepositEntity entity) {
        return GiftDepositDto.builder()
                .id(entity.getId())
                .companyId(entity.getCompanyId())
                .employeeId(entity.getEmployeeId())
                .amount(entity.getAmount())
                .depositDate(entity.getDepositDate())
                .expirationDate(entity.getExpirationDate())
                .build();
    }

    public static MealDepositDto toMealDeposit(MealDepositEntity entity) {
        return MealDepositDto.builder()
                .id(entity.getId())
                .companyId(entity.getCompanyId())
                .employeeId(entity.getEmployeeId())
                .amount(entity.getAmount())
                .depositDate(entity.getDepositDate())
                .expirationDate(entity.getExpirationDate())
                .build();
    }

}
