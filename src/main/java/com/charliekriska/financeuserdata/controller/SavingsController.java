package com.charliekriska.financeuserdata.controller;

import com.charliekriska.financeuserdata.model.Savings;
import com.charliekriska.financeuserdata.service.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class SavingsController {

    @Autowired
    SavingsService savingsService;

    @GetMapping("/savings/{userId}")
    public Savings getSavingsByUserId(@PathVariable int userId) {
        return savingsService.getSavingsByUserId(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/savings")
    public Savings addSavings(@RequestBody Savings savings) {
        return savingsService.addSavings(savings);
    }

}
