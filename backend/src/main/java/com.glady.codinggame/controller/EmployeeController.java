package com.glady.codinggame.controller;

import com.glady.codinggame.dto.EmployeeDto;
import com.glady.codinggame.exception.GladyException;
import com.glady.codinggame.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @ApiOperation(value = "Add a new employee.", produces = "application/json")
    @RequestMapping(value = "", method = RequestMethod.POST,  consumes = "application/json", produces = "application/json")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto)  {
        return new ResponseEntity<>(employeeService.create(employeeDto), HttpStatus.OK);
    }

    @ApiOperation(value = "get the mealcash balance.")
    @RequestMapping(value = "/{employeeId}/meal-balance", method = RequestMethod.GET)
    public ResponseEntity<BigDecimal> getEmployeeMealCashBalance(@PathVariable Long employeeId)  throws GladyException  {
        return new ResponseEntity<>(employeeService.getUserMealBalance(employeeId), HttpStatus.OK);
    }

    @ApiOperation(value = "get the Gift cash balance.")
    @RequestMapping(value = "/{employeeId}/gift-balance", method = RequestMethod.GET)
    public ResponseEntity<BigDecimal> getEmployeeGiftCashBalance(@PathVariable Long employeeId)  throws GladyException {
        return new ResponseEntity<>(employeeService.getUserGiftBalance(employeeId), HttpStatus.OK);
    }
}
