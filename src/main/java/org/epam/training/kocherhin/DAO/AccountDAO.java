package org.epam.training.kocherhin.DAO;

import org.epam.training.kocherhin.Entity.Account;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AccountDAO {

    public List<Account> getByUser(long userId) throws SQLException {
        List<Account> accounts = new LinkedList<>();
        try(Connection connection = ConnectionProvider.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(Queries.GET_ACCOUNTS_BY_USER)) {
            statement.setLong(1, userId);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Account account = new Account();
                    account.setId(resultSet.getLong("id"));
                    account.setCardNumber(resultSet.getString("card_number"));
                    account.setName(resultSet.getString("name"));
                    account.setAmount(resultSet.getInt("amount"));
                    account.setCurrency(Account.Currency.valueOf(resultSet.getString("currency")));
                    account.setUserId(resultSet.getLong("user_id"));
                    account.setBlocked(resultSet.getBoolean("blocked"));
                    accounts.add(account);
                }
            }
        }
        return accounts;
    }
}
