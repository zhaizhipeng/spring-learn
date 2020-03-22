package com.ysdrzp.jdbctemplate;

import com.ysdrzp.model.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountResultSetExtractor implements ResultSetExtractor<Account> {

    @Override
    public Account extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Account account = new Account();
        if (resultSet != null){
            account.setId(resultSet.getInt("id"));
            account.setName(resultSet.getString("name"));
            account.setMoney(resultSet.getFloat("money"));
        }
        return account;
    }
}
