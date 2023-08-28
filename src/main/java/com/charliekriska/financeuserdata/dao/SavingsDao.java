package com.charliekriska.financeuserdata.dao;

import com.charliekriska.financeuserdata.model.Savings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SavingsDao {

    @Autowired
    JdbcTemplate jdbc;

    public Savings getSavingsByUserId(int userId) {
        try {
            final String SELECT_SAVINGS_BY_USER_ID = "SELECT * FROM savingsdata WHERE userId = ?;";
            Savings savings = jdbc.queryForObject(SELECT_SAVINGS_BY_USER_ID, new SavingsMapper(), userId);
            return savings;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    public static final class SavingsMapper implements RowMapper<Savings> {

        @Override
        public Savings mapRow(ResultSet rs, int rowNum) throws SQLException {
            Savings savings = new Savings();
            savings.setSavingsDataId(rs.getInt("savingsDataId"));
            savings.setUserId(rs.getInt("userId"));
            savings.setIncome(rs.getDouble("income"));
            savings.setExpenses(rs.getDouble("expenses"));
            savings.setCurrentSavings(rs.getDouble("currentSavings"));
            savings.setExpectedReturn(rs.getDouble("expectedReturn"));
            return savings;
        }
    }

}
