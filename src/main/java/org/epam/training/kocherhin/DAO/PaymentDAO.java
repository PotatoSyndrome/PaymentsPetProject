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

    public Payment getById(Long paymentId) throws SQLException {
        return mapper.mapOne(new PreparedSqlQuery(
                Queries.GET_PAYMENT_BY_ID,
                paymentId));
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

    public List<Payment> getByFromUserWithPagination(User user, int page,
            int recordsPerPage) throws SQLException {
        return mapper.mapAll(new PreparedSqlQuery(
                Queries.GET_PAYMENTS_BY_FROM_USER_WITH_PAGINATION,
                user.getId(),
                (page - 1) * recordsPerPage,
                recordsPerPage));
    }

    public List<Payment> getByFromUserWithSort(User user,String orderBy, boolean asc, int page,
                                                     int recordsPerPage) throws SQLException {
        return mapper.mapAll(new PreparedSqlQuery(
                Queries.GET_PAYMENTS_WITH_SORTING_AND_PAGINATION.
                        replace("*limiter*", (String) GeneralDAO.escapeForLike(orderBy)).
                        replace("*descender*", asc ? "ASC" : "DESC"),
                user.getId(),
                (page - 1) * recordsPerPage,
                recordsPerPage));
    }

    public int getNumberOfPayments(User user) throws SQLException {
        return mapper.mapAll(new PreparedSqlQuery(
                Queries.GET_PAYMENTS_BY_FROM_USER, user.getId()
        )).size();
    }

    public void insertPayment(Payment payment) throws SQLException {
        mapper.commitAll(new PreparedSqlQuery(
            Queries.INSERT_PAYMENT,
                payment.getFrom(),
                payment.getTo(),
                payment.getAmount()
        ));
    }

    public List<Payment> getReadyWithPagination(int page, int recordPerPage) throws SQLException {
        return mapper.mapAll(new PreparedSqlQuery(
            Queries.GET_READY_PAYMENTS_WITH_PAGINATION,
                (page - 1) * recordPerPage,
                recordPerPage));
    }

    public int getNumberOfReadyPayments() throws SQLException {
        return mapper.mapAll(new PreparedSqlQuery(
                Queries.GET_READY_PAYMENTS)).size();
    }

    public void declinePayment(long paymentId) throws SQLException {
        mapper.commitAll(new PreparedSqlQuery(
                Queries.DECLINE_PAYMENT,
                paymentId
        ));
    }

    public void confirmPayment(long paymentId) throws SQLException {
        Payment payment = getById(paymentId);
        Account from = new AccountDAO().getById(payment.getFrom());
        Account to = new AccountDAO().getById(payment.getTo());
        int paidAmount = payment.getAmount();
        int destinationAmount = Account.Currency.convert(from.getCurrency(), to.getCurrency(), paidAmount);

        mapper.commitAll(new PreparedSqlQuery(
                Queries.CHANGE_AMOUNT_IN_ACCOUNT,
                -paidAmount,
                from.getId()),
                new PreparedSqlQuery(
                Queries.CHANGE_AMOUNT_IN_ACCOUNT,
                destinationAmount,
                to.getId()),
                new PreparedSqlQuery(
                Queries.SEND_PAYMENT,
                paymentId));
    }

    private static class PaymentMapper implements EntityMapper<Payment> {

        @Override
        public Payment mapObject(ResultSet resultSet) throws SQLException {
            Payment payment = new Payment();
            payment.setId(resultSet.getLong("id"));
            payment.setFrom(resultSet.getLong("from_account"));
            payment.setFromNumber(resultSet.getString("from_number"));
            payment.setTo(resultSet.getLong("to_account"));
            payment.setToNumber(resultSet.getString("to_number"));
            payment.setAmount(resultSet.getInt("amount"));
            payment.setCurrency(resultSet.getString("currency"));
            payment.setStatus(Payment.Status.valueOf(resultSet.getString("status")));
            payment.setTime(resultSet.getString("time"));
            return payment;
        }
    }
}
