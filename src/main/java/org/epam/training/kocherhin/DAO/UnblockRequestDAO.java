package org.epam.training.kocherhin.DAO;

import org.epam.training.kocherhin.Entity.Account;
import org.epam.training.kocherhin.Entity.UnblockRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UnblockRequestDAO {
    private GeneralDAO<UnblockRequest> mapper;

    public UnblockRequestDAO() {
        mapper = new GeneralDAO<>(new UnlockRequestMapper());
    }

    public List<UnblockRequest> getUnconsideredRequestsWithPagination(int page, int recordPerPage) throws SQLException {
        return mapper.mapAll(new PreparedSqlQuery(
                Queries.GET_UNCONSIDERED_REQUESTS_WITH_PAGINATION,
                (page - 1) * recordPerPage,
                recordPerPage));
    }

    public int getNumberOfUnconsideredRequests() throws SQLException {
        return mapper.mapAll(new PreparedSqlQuery(
                Queries.GET_UNCONSIDERED_REQUESTS
        )).size();
    }

    public void considerRequest(long adminId, Long requestId) throws SQLException {
        mapper.commitAll(new PreparedSqlQuery(
                Queries.CONSIDER_REQUEST,
                adminId,
                requestId));
    }

    public void addRequest(Long accountId) throws SQLException {
        mapper.commitAll(new PreparedSqlQuery(
                Queries.DELETE_OLD_REQUEST,
                accountId),
                new PreparedSqlQuery(
                Queries.ADD_REQUEST,
                accountId));
    }


    private static class UnlockRequestMapper implements EntityMapper<UnblockRequest> {

        @Override
        public UnblockRequest mapObject(ResultSet resultSet) throws SQLException {
            Account account = new Account();

            account.setId(resultSet.getLong("id"));
            account.setCardNumber(resultSet.getString("card_number"));
            account.setName(resultSet.getString("name"));
            account.setAmount(resultSet.getInt("amount"));
            account.setCurrency(Account.Currency.valueOf(resultSet.getString("currency")));
            account.setUserId(resultSet.getLong("user_id"));
            account.setBlocked(resultSet.getBoolean("blocked"));

            UnblockRequest unblockRequest = new UnblockRequest();
            unblockRequest.setAccount(account);
            unblockRequest.setChangedBy(resultSet.getLong("changed_by"));
            unblockRequest.setUserLogin(resultSet.getString("login"));
            unblockRequest.setConsidered(resultSet.getBoolean("considered"));

            return unblockRequest;
        }
    }
}
