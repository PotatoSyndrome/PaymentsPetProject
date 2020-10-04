package org.epam.training.kocherhin.DAO;

import org.epam.training.kocherhin.Entity.Account;
import org.epam.training.kocherhin.Entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDAO {
    GeneralDAO<Account> mapper;

    public AccountDAO() {
        mapper = new GeneralDAO<>(new AccountMapper());
    }

    public List<Account> getByUser(User user) throws SQLException {
        return mapper.mapAll(new PreparedSqlQuery(
                Queries.GET_ACCOUNTS_BY_USER, Long.toString(user.getId())));
    }

    private static class AccountMapper implements EntityMapper<Account> {

        @Override
        public Account mapObject(ResultSet resultSet) throws SQLException {
            Account account = new Account();

            account.setId(resultSet.getLong("id"));
            account.setCardNumber(resultSet.getString("card_number"));
            account.setName(resultSet.getString("name"));
            account.setAmount(resultSet.getInt("amount"));
            account.setCurrency(Account.Currency.valueOf(resultSet.getString("currency")));
            account.setUserId(resultSet.getLong("user_id"));
            account.setBlocked(resultSet.getBoolean("blocked"));

            return account;
        }
    }
}
