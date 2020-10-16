package org.epam.training.kocherhin.DAO;

import org.junit.Test;

import java.sql.SQLException;

public class PaymentDAOTest {

    @Test
    public void tryAll() throws SQLException {
        PaymentDAO paymentDAO = new PaymentDAO();

        paymentDAO.getById(1L);

        paymentDAO.getReadyWithPagination(1, 2);

        paymentDAO.getNumberOfReadyPayments();

        paymentDAO.confirmPayment(1);

        paymentDAO.declinePayment(1);
    }
}
