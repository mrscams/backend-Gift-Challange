package com.glady.codinggame.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity(name = "gift_deposit")
@DiscriminatorValue("GIFT_DEPOSIT")
public class GiftDepositEntity extends DepositEntity {

    public GiftDepositEntity(Long companyId, Long employeeId, BigDecimal amount, LocalDate depositDate, LocalDate expirationDate) {
        super(companyId, employeeId, amount, depositDate, expirationDate);
    }

}
