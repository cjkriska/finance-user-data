package com.charliekriska.financeuserdata.controller;

import com.charliekriska.financeuserdata.model.Savings;
import com.charliekriska.financeuserdata.service.SavingsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class SavingsControllerTest {

    @InjectMocks
    private SavingsController savingsController;

    @Mock
    private SavingsService savingsService;

    @Test
    void testGetSavingsByUserId() throws Exception {

        when(savingsService.getSavingsByUserId(anyInt())).thenReturn(savingsSample());
        Savings result = savingsController.getSavingsByUserId(1);
        assertEquals(result.getUserId(), 1);
        assertEquals(result.getSavingsDataId(), 2);
        assertEquals(result.getIncome(), 50000);

    }
    @Test
    void testAddSavings() throws Exception {

        when(savingsService.addSavings(any())).thenReturn(savingsSample());
        Savings result = savingsController.addSavings(savingsSample());
        assertEquals(result.getUserId(), 1);
        assertEquals(result.getSavingsDataId(), 2);
        assertEquals(result.getIncome(), 50000);

    }

    public Savings savingsSample() {
        return Savings.builder()
                .savingsDataId(2)
                .userId(1)
                .income(50000)
                .expenses(35000)
                .currentSavings(5000)
                .expectedReturn(7)
                .build();
    }


}