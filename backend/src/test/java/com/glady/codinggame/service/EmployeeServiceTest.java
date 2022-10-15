package com.glady.codinggame.service;

import com.glady.codinggame.dto.EmployeeDto;
import com.glady.codinggame.exception.UserNotFoundException;
import com.glady.codinggame.repository.EmployeeRepository;
import com.glady.codinggame.repository.GiftDepositRepository;
import com.glady.codinggame.repository.MealDepositRepository;
import com.glady.codinggame.repository.entity.EmployeeEntity;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {


    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private GiftDepositRepository giftDepositRepository;
    @Mock
    private MealDepositRepository mealDepositRepository;

    @InjectMocks
    private EmployeeService employeeService ;



    @Test
    public void create() {

        EmployeeDto employeeDto = EmployeeDto.builder().name("wadio").build();
        EmployeeEntity entity = new EmployeeEntity(employeeDto.getId(), employeeDto.getName());

        when(employeeRepository.saveAndFlush(entity)).thenReturn(entity);
        EmployeeDto result = employeeService.create(employeeDto);
        assertEquals(employeeDto.getName(), result.getName());

    }


    @Test
    public  void should_Not_exist_employee() {
        assertFalse( this.employeeService.existsById(14664326L));
    }


    @Test
    public  void should_exist_employee() {

        when(employeeRepository.existsById(1L)).thenReturn(true);

        assertTrue( this.employeeService.existsById(1L));
    }

    @Test
    public void Should_throw_When_Employee_not_exist_getUserGiftBalance() {
        when(employeeRepository.existsById(1L)).thenReturn(false);

        Assertions.assertThatThrownBy(() ->  this.employeeService.getUserGiftBalance(1L))
                .isExactlyInstanceOf(UserNotFoundException.class);

    }

    @Test
    public void getUserGiftBalance() {
        when(employeeRepository.existsById(1L)).thenReturn(true);
        when(giftDepositRepository.getUserBalance(1L)).thenReturn(new BigDecimal(50));

        assertTrue(employeeService.getUserGiftBalance(1L).equals(new BigDecimal(50)));

    }

    @Test
    public void Should_throw_When_Employee_not_exist_getUserMealBalance() {
        when(employeeRepository.existsById(1L)).thenReturn(false);

        Assertions.assertThatThrownBy(() ->  this.employeeService.getUserMealBalance(1L))
                .isExactlyInstanceOf(UserNotFoundException.class);
    }

    @Test
    public void getUserMealBalance() {
        when(employeeRepository.existsById(1L)).thenReturn(true);
        when(mealDepositRepository.getUserBalance(1L)).thenReturn(new BigDecimal(50));

        assertTrue(employeeService.getUserMealBalance(1L).equals(new BigDecimal(50)));
    }
}