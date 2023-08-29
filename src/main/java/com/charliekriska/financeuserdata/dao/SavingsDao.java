package com.charliekriska.financeuserdata.dao;

import com.charliekriska.financeuserdata.model.Savings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Savings addSavings(Savings savings) {
        final String INSERT_SAVINGS = "INSERT INTO savingsdata(userId, income, expenses, currentSavings, expectedReturn) " +
                                        "VALUES(?,?,?,?,?)";

        jdbc.update(INSERT_SAVINGS,
                savings.getUserId(),
                savings.getIncome(),
                savings.getExpenses(),
                savings.getCurrentSavings(),
                savings.getExpectedReturn()
                );
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        savings.setSavingsDataId(newId);

        return savings;
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
