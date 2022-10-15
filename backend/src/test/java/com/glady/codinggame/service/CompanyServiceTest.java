package com.glady.codinggame.service;

import com.glady.codinggame.dto.CompanyDto;
import com.glady.codinggame.exception.CompanyNotFoundException;
import com.glady.codinggame.exception.InsufficientCashException;
import com.glady.codinggame.repository.CompanyRepository;
import com.glady.codinggame.repository.entity.CompanyEntity;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    CompanyEntity company = new CompanyEntity(1l,"wadio company",new BigDecimal(1000),new BigDecimal(1000));
    CompanyDto companyDto = CompanyDto.builder().name("wadio Company").giftCash(new BigDecimal(1000)).mealCash(new BigDecimal(1000)).build();


    @Test
    public void should_create() {

        CompanyEntity entity = new CompanyEntity(companyDto.getId(), companyDto.getName(),companyDto.getGiftCash(),companyDto.getMealCash());

        when(companyRepository.saveAndFlush(entity)).thenReturn(entity);
        CompanyDto result = companyService.create(companyDto);
        assertEquals(companyDto.getName(), result.getName());

    }

    @Test
    public void getCompanyById() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        CompanyDto companyDto = this.companyService.getCompanyById(1L);
        assertEquals(companyDto.getName(),"wadio company");
    }

    @Test
    public void should_throw_if_Not_exist_getCompanyById() {
        when(companyRepository.findById(111L)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() ->  this.companyService.getCompanyById(111L))
                .isExactlyInstanceOf(CompanyNotFoundException.class);
    }

    @Test
    public void distributeGiftCash() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        BigDecimal newBalance = companyService.distributeGiftCash(1l,new BigDecimal(50));

        assertEquals(newBalance , BigDecimal.valueOf(950) );

    }

    @Test
    public void should_throw_distributeGiftCash() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        Assertions.assertThatThrownBy(() ->  this.companyService.distributeGiftCash(1l,new BigDecimal(2000)))
                .isExactlyInstanceOf(InsufficientCashException.class);
    }

    @Test
    public void hasEnoughGiftCash() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        assertTrue(companyService.hasEnoughGiftCash(1l,BigDecimal.valueOf(100)));

    }

    @Test
    public void has_Not_EnoughGiftCash() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        assertFalse(companyService.hasEnoughGiftCash(1l,BigDecimal.valueOf(2100)));

    }

}