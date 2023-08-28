package com.charliekriska.financeuserdata.controller;

import com.charliekriska.financeuserdata.model.Savings;
import com.charliekriska.financeuserdata.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SavingsController {

    @Autowired
    SavingsService savingsService;

    @GetMapping("/savings/{userId}")
    public Savings getSavingsByUserId(@PathVariable int userId) {
        return savingsService.getSavingsByUserId(userId);
    }

}
