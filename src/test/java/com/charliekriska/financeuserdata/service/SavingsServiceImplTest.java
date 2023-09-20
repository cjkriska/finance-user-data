package com.charliekriska.financeuserdata.service;

import com.charliekriska.financeuserdata.dao.SavingsDao;
import com.charliekriska.financeuserdata.model.Savings;
import com.charliekriska.financeuserdata.utility.Samples;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SavingsServiceImplTest {

    @InjectMocks
    private SavingsServiceImpl savingsService;

    @Mock
    private SavingsDao savingsDao;

    @Test
    void testGetSavingsByUserId() {

        when(savingsDao.getSavingsByUserId(anyInt())).thenReturn(Samples.savingsSampleOutput());
        Savings result = savingsService.getSavingsByUserId(1);
        assertEquals(result.getUserId(), 1);
        assertEquals(result.getSavingsDataId(), 2);
        assertEquals(result.getIncome(), 50000);

    }

    @Test
    void testAddSavings() {

        when(savingsDao.addSavings(any())).thenReturn(Samples.savingsSampleOutput());
        Savings result = savingsService.addSavings(Samples.savingsSampleInput());
        assertEquals(result.getUserId(), 1);
        assertEquals(result.getSavingsDataId(), 2);
        assertEquals(result.getIncome(), 50000);

    }

}
