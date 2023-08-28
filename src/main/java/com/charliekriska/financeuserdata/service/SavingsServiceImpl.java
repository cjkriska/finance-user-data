package com.charliekriska.financeuserdata.service;

import com.charliekriska.financeuserdata.dao.SavingsDao;
import com.charliekriska.financeuserdata.model.Savings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingsServiceImpl implements SavingsService {

    @Autowired
    SavingsDao savingsDao;

    @Override
    public Savings getSavingsByUserId(int userId) {
        return savingsDao.getSavingsByUserId(userId);
    }
}
