package com.glady.codinggame.repository.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity(name = "employee")
public class EmployeeEntity  {

    @NotNull
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name="employeeId")
    private List<GiftDepositEntity> giftBalanceList ;


    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name="employeeId")
    private List<MealDepositEntity> mealBalanceList ;

    public EmployeeEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || this.getClass() != o.getClass())
            return false;

        EmployeeEntity entity = (EmployeeEntity) o;

        return  Objects.equals(this.id, entity.id)
                && Objects.equals(this.name, entity.name)
                && Objects.equals(this.giftBalanceList, entity.giftBalanceList)
                && Objects.equals(this.mealBalanceList, entity.mealBalanceList);
    }

}
