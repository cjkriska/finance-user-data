package com.charliekriska.financeuserdata.dao;

import com.charliekriska.financeuserdata.model.Savings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SavingsDaoTest {

    @InjectMocks
    private SavingsDao savingsDao;

    @Mock
    private JdbcTemplate jdbc;

    @Test
    void testGetSavingsByUserId() {

        when(jdbc.queryForObject(eq("SELECT * FROM savingsdata WHERE userId = ?;"), any(SavingsDao.SavingsMapper.class), anyInt())).thenReturn(savingsSampleOutput());
        Savings result = savingsDao.getSavingsByUserId(1);
        assertEquals(result.getUserId(), 1);
        assertEquals(result.getSavingsDataId(), 2);
        assertEquals(result.getIncome(), 50000);

    }

    @Test
    void testAddSavings() {

        final String INSERT_SAVINGS = "INSERT INTO savingsdata(userId, income, expenses, currentSavings, expectedReturn) " +
                "VALUES(?,?,?,?,?)";

        when(jdbc.update(any(String.class),
                anyInt(),
                anyDouble(),
                anyDouble(),
                anyDouble(),
                anyDouble()))
        .then(invocation -> {
            assertEquals(invocation.getArgument(0), INSERT_SAVINGS);
            assertEquals((Integer) invocation.getArgument(1), 1);
            assertEquals(invocation.getArgument(2), 50000.00);
            assertEquals(invocation.getArgument(3), 35000.00);
            assertEquals(invocation.getArgument(4), 5000.00);
            assertEquals(invocation.getArgument(5), 7.0);
            return 1;
        });

        when(jdbc.queryForObject(any(String.class), any(Class.class)))
                .thenReturn(2);

        Savings result = savingsDao.addSavings(savingsSampleInput());
        assertEquals(result.getSavingsDataId(), 2);

    }

    public Savings savingsSampleInput() {
        return Savings.builder()
                .userId(1)
                .income(50000)
                .expenses(35000)
                .currentSavings(5000)
                .expectedReturn(7)
                .build();
    }

    public Savings savingsSampleOutput() {
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
