package com.glady.codinggame.service;

import com.glady.codinggame.dto.CompanyDto;
import com.glady.codinggame.dto.GiftDepositDto;
import com.glady.codinggame.repository.CompanyRepository;
import com.glady.codinggame.repository.EmployeeRepository;
import com.glady.codinggame.repository.GiftDepositRepository;
import com.glady.codinggame.repository.entity.CompanyEntity;
import com.glady.codinggame.repository.entity.GiftDepositEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DepositDistributionServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private GiftDepositRepository giftDepositRepository;
    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyService companyService;

    CompanyEntity company = new CompanyEntity(1l,"wadio company",new BigDecimal(1000),new BigDecimal(1000));
    GiftDepositDto giftDepositDto = new GiftDepositDto(1l,2l,BigDecimal.valueOf(50),null,null);
    CompanyDto companyDto = CompanyDto.builder().name("wadio Company").giftCash(new BigDecimal(1000)).mealCash(new BigDecimal(1000)).build();

    @InjectMocks
    private DepositDistributionService depositDistributionService ;

    @Mock
    private EmployeeService employeeService;

    @Test
    public void distributeGiftDeposits() {

        LocalDate giftExpirationDate = depositDistributionService.getGiftExpirationDate(LocalDate.now());
        GiftDepositEntity giftDepositEntity = new GiftDepositEntity(1l,2l,BigDecimal.valueOf(50), LocalDate.now(), giftExpirationDate);
        giftDepositEntity.setId(100l);

        when(employeeRepository.existsById(2l)).thenReturn(true);
        when(companyService.getCompanyById(1l)).thenReturn(companyDto);
        when(employeeService.existsById(2l)).thenReturn(true);
        when(companyRepository.findById(1l)).thenReturn(Optional.of(company));
        when(giftDepositRepository.saveAndFlush(any(GiftDepositEntity.class))).thenReturn(giftDepositEntity);

        GiftDepositDto giftDepositDtoResult = depositDistributionService.distributeGiftDeposits(giftDepositDto);
        assertTrue(giftDepositDtoResult.getExpirationDate().isEqual(giftExpirationDate));
    }

    @Test
    public void createGiftDeposits() {
    }

    @Test
    public void getGiftExpirationDate() {
    }

}