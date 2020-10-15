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
                Queries.GET_ACCOUNTS_BY_USER,
                Long.toString(user.getId())));
    }

    public List<Account> getByUserWithPagination(User user, int page, int accountsOnPage) throws SQLException {
        return mapper.mapAll(new PreparedSqlQuery(
                Queries.GET_ACCOUNTS_BY_USER_WITH_PAGINATION,
                Long.toString(user.getId()),
                (page - 1) * accountsOnPage,
                accountsOnPage));
    }

    public List<Account> getByUserIdWithPagination(long userId, int page, int accountsOnPage) throws SQLException {
        return mapper.mapAll(new PreparedSqlQuery(
                Queries.GET_ACCOUNTS_BY_USER_WITH_PAGINATION,
                userId,
                (page - 1) * accountsOnPage,
                accountsOnPage));
    }

    public int getNumberOfAccounts(User user) throws SQLException {
        return getByUser(user).size();
    }

    public void insertAccount(Account account) throws SQLException {
        mapper.commitAll(new PreparedSqlQuery(
                Queries.INSERT_ACCOUNT,
                account.getCardNumber(),
                account.getName(),
                account.getAmount(),
                account.getCurrency().toString(),
                account.getUserId()));
    }

    public Account getById(long id) throws SQLException {
        return mapper.mapOne(new PreparedSqlQuery(
                Queries.GET_ACCOUNT_BY_ID,
                id));
    }

    public Account getByCardNumber(String cardNumber) throws SQLException {
        return mapper.mapOne(new PreparedSqlQuery(
                Queries.GET_ACCOUNT_BY_CARD_NUMBER,
                cardNumber));
    }

    public void blockAccount(Long id) throws SQLException {
        mapper.commitAll(new PreparedSqlQuery(
                Queries.BLOCK_ACCOUNT,
                id),
                new PreparedSqlQuery(
                Queries.DECLINE_ALL_READY_PAYMENTS,
                id));
    }

    public void unblockAccount(Long id) throws SQLException {
        mapper.commitAll(new PreparedSqlQuery(
                        Queries.UNBLOCK_ACCOUNT,
                        id));
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
