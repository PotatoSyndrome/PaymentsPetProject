package org.epam.training.kocherhin.DAO;

import org.junit.Test;

import java.sql.SQLException;

public class UnblockRequestDAOTest {

    @Test
    public void tryAll() throws SQLException {
        UnblockRequestDAO unblockRequestDAO = new UnblockRequestDAO();

        unblockRequestDAO.considerRequest(1, 1L);

        unblockRequestDAO.getNumberOfUnconsideredRequests();

        unblockRequestDAO.getUnconsideredRequestsWithPagination(1,5);
    }
}
