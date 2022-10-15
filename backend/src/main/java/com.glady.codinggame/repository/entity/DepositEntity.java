package com.glady.codinggame.repository.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DEPOSIT_TYPE")
public abstract class DepositEntity extends BaseEntity {

    @NotNull
    @Column(name = "companyId", nullable = false)
    private Long companyId;

    @NotNull
    @Column(name = "employeeId", nullable = false)
    private Long employeeId;

    @NotNull
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @NotNull
    @Column(name = "depositDate", nullable = false)
    private LocalDate depositDate;

    @NotNull
    @Column(name = "expirationDate", nullable = false)
    private LocalDate expirationDate;


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || this.getClass() != o.getClass()) return false;

        DepositEntity entity = (DepositEntity) o;

        return super.equals(entity)
                && Objects.equals(this.companyId, entity.companyId)
                && Objects.equals(this.employeeId, entity.employeeId)
                && Objects.equals(this.amount, entity.amount)
                && Objects.equals(this.depositDate, entity.depositDate)
                && Objects.equals(this.expirationDate, entity.expirationDate);
    }

}
