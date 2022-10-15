package com.glady.codinggame.service;

import com.glady.codinggame.dto.EmployeeDto;
import com.glady.codinggame.exception.UserNotFoundException;
import com.glady.codinggame.repository.EmployeeRepository;
import com.glady.codinggame.repository.GiftDepositRepository;
import com.glady.codinggame.repository.entity.EmployeeEntity;
import com.glady.codinggame.repository.entity.GiftDepositEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final GiftDepositRepository giftDepositRepository;


    public EmployeeDto create(EmployeeDto employeeDto) {
        EmployeeEntity entity = new EmployeeEntity(employeeDto.getId(), employeeDto.getName());

        EmployeeDto created = Optional.of(entity)
                .map(this.employeeRepository::saveAndFlush)
                .map(EmployeeService::toEmployee)
                .orElseThrow();

        log.info("Employee created : "+ created);

        return created;
    }

    public boolean existsById(Long employeeId) {
        return this.employeeRepository.existsById(employeeId);
    }

    public BigDecimal getUserGiftBalance(Long employeeId) throws UserNotFoundException {
        boolean exists = this.existsById(employeeId);

        if (!exists)
            throw new UserNotFoundException();

        return this.giftDepositRepository.getUserBalance(employeeId);
    }

    private static EmployeeDto toEmployee(EmployeeEntity entity) {
        EmployeeDto employeeDto =  EmployeeDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        List<GiftDepositEntity> giftBalance = entity.getGiftBalanceList();
        if (giftBalance != null) {
            employeeDto.setGiftDeposits(giftBalance.stream().map(DepositDistributionService::toGiftDeposit).collect(Collectors.toList()));
        }

        return employeeDto;
    }
}