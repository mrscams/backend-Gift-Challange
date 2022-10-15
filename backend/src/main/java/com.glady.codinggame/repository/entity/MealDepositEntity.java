package com.glady.codinggame.repository.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity(name = "meal_deposit")
@DiscriminatorValue("MEAL_DEPOSIT")
public class MealDepositEntity extends DepositEntity {

    public MealDepositEntity(Long companyId, Long employeeId, BigDecimal amount, LocalDate depositDate, LocalDate expirationDate) {
        super(companyId, employeeId, amount, depositDate, expirationDate);
    }

}
