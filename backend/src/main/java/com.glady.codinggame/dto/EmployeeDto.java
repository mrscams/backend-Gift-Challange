package com.glady.codinggame.dto;

import java.util.List;

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
public class EmployeeDto {

    private Long id;
    private String name;

    private List<GiftDepositDto> giftDeposits;

    public EmployeeDto(String name) {
        this.name = name;
    }
}
