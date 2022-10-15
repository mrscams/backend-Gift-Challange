package com.glady.codinggame.controller;

import com.glady.codinggame.dto.GiftDepositDto;
import com.glady.codinggame.dto.MealDepositDto;
import com.glady.codinggame.exception.GladyException;
import com.glady.codinggame.service.DepositDistributionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deposit-distribution")
public class DepositDistributionController {

    @Autowired
    DepositDistributionService depositDistributionService;


    @ApiOperation(value = "deposit a gift cash for a given employee in a company" , produces = "application/json")
    @RequestMapping(value = "/gift", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<GiftDepositDto> addGiftDeposit(@RequestBody GiftDepositDto giftDeposit) throws GladyException {
        return new ResponseEntity<>(depositDistributionService.distributeGiftDeposits(giftDeposit), HttpStatus.OK);
    }
}
