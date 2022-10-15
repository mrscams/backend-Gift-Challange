package com.glady.codinggame.dto;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CompanyDto {

    private Long id;

    private String name;

    private BigDecimal giftCash =  BigDecimal.ZERO ;

    private BigDecimal mealCash =  BigDecimal.ZERO;


    public CompanyDto(String name) {
        this.name = name;
    }

}
