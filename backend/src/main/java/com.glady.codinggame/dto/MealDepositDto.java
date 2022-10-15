package com.glady.codinggame.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MealDepositDto extends DepositDto {

    public MealDepositDto(Long companyId,  Long employeeId,  BigDecimal amount,  LocalDate depositDate,  LocalDate expirationDate) {
        super(companyId, employeeId, amount, depositDate, expirationDate);
    }


    @Override
    public DepositType getType() {
        return DepositType.MEAL;
    }

}
