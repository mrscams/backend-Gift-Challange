package com.glady.codinggame.service;

import com.glady.codinggame.dto.GiftDepositDto;
import com.glady.codinggame.exception.CompanyNotFoundException;
import com.glady.codinggame.exception.IllegalAmountException;
import com.glady.codinggame.exception.UserNotFoundException;
import com.glady.codinggame.repository.GiftDepositRepository;
import com.glady.codinggame.repository.entity.GiftDepositEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DepositDistributionService {

    private final CompanyService companyService;
    private final EmployeeService employeeService;
    private final GiftDepositRepository giftDepositRepository;


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

    public LocalDate getGiftExpirationDate(LocalDate giftDepositDate) {
        return giftDepositDate.plusDays(364);
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


}
