package com.glady.codinggame.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public abstract class DepositDto {

    private Long id;

    private Long companyId;

    private Long employeeId;

    private BigDecimal amount;

    private LocalDate depositDate;

    private LocalDate expirationDate;


    protected DepositDto(Long companyId, Long employeeId, BigDecimal amount, LocalDate depositDate, LocalDate expirationDate) {
        this.companyId = companyId;
        this.employeeId = employeeId;
        this.amount = amount;
        this.depositDate = depositDate;
        this.expirationDate = expirationDate;
    }


    public abstract  DepositType getType();

}
