package org.epam.training.kocherhin.DAO;

import org.epam.training.kocherhin.Entity.Account;
import org.epam.training.kocherhin.Entity.Payment;
import org.epam.training.kocherhin.Entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaymentDAO {

    private GeneralDAO<Payment> mapper;

    public PaymentDAO() {
        mapper = new GeneralDAO<>(new PaymentMapper());
    }

    public List<Payment> getByFromAccount(Account account) throws SQLException {
        return mapper.mapAll(new PreparedSqlQuery(
                Queries.GET_PAYMENTS_BY_FROM_ACCOUNT,
                Long.toString(account.getId())));
    }

    public List<Payment> getByDestinationAccount(Account account) throws SQLException {
        return mapper.mapAll(new PreparedSqlQuery(
                Queries.GET_PAYMENTS_BY_TO_ACCOUNT,
                Long.toString(account.getId())));
    }

    public List<Payment> getByFromUserWithPagination(User user, int page, int accountsOnPage) throws SQLException {
        return mapper.mapAll(new PreparedSqlQuery(
                Queries.GET_PAYMENTS_BY_FROM_USER_WITH_PAGINATION,
                user.getId(),
                (page - 1) * accountsOnPage,
                accountsOnPage));
    }

    public int getNumberOfPayments(User user) throws SQLException {
        return mapper.mapAll(new PreparedSqlQuery(
                Queries.GET_PAYMENTS_BY_FROM_USER, user.getId()
        )).size();
    }

    private static class PaymentMapper implements EntityMapper<Payment> {

        @Override
        public Payment mapObject(ResultSet resultSet) throws SQLException {
            Payment payment = new Payment();
            payment.setId(resultSet.getLong("id"));
            payment.setFrom(resultSet.getLong("from_account"));
            payment.setTo(resultSet.getLong("to_account"));
            payment.setAmount(resultSet.getInt("amount"));
            payment.setStatus(Payment.Status.valueOf(resultSet.getString("status")));
            payment.setTime(resultSet.getTime("time"));
            return payment;
        }
    }
}
