package com.charliekriska.financeuserdata.controller;

import com.charliekriska.financeuserdata.model.Savings;
import com.charliekriska.financeuserdata.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SavingsController {

    @Autowired
    SavingsService savingsService;

    @GetMapping("/savings/{userId}")
    public Savings getSavingsByUserId(@PathVariable int userId) {
        return savingsService.getSavingsByUserId(userId);
    }

    @PostMapping("/savings")
    public Savings addSavings(@RequestBody Savings savings) {
        return savingsService.addSavings(savings);
    }

}
