package com.glady.codinggame.repository.entity;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "company")
public class CompanyEntity  {

    @NotNull
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    protected Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "gift_cash", nullable = false)
    private BigDecimal giftCash = BigDecimal.ZERO;

    @NotNull
    @Column(name = "meal_cash", nullable = false)
    private BigDecimal mealCash = BigDecimal.ZERO;


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || this.getClass() != o.getClass())
            return false;
        CompanyEntity entity = (CompanyEntity) o;

        return Objects.equals(this.id, entity.id)
                && Objects.equals(this.name, entity.name)
                && Objects.equals(this.giftCash, entity.giftCash)
                && Objects.equals(this.mealCash, entity.mealCash);
    }

}
