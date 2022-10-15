package com.glady.codinggame.dto;

import java.math.BigDecimal;
import java.time.LocalDate;


import lombok.experimental.SuperBuilder;

@SuperBuilder
public class GiftDepositDto extends DepositDto {

    public GiftDepositDto(Long companyId, Long employeeId, BigDecimal amount, LocalDate depositDate, LocalDate expirationDate) {
        super(companyId, employeeId, amount, depositDate, expirationDate);
    }


    @Override
    public DepositType getType() {
        return DepositType.GIFT;
    }

}
