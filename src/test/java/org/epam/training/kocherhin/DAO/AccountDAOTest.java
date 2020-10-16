package org.epam.training.kocherhin.DAO;

import org.epam.training.kocherhin.Entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class AccountDAOTest {

    @Test
    public void tryAll() throws SQLException {
        AccountDAO accountDAO = new AccountDAO();

        accountDAO.getByUser(new User());

        accountDAO.unblockAccount(1L);

        accountDAO.getByUserWithPagination(new User(), 1, 1);

        accountDAO.getByUserIdWithPagination(1, 1, 1);

        accountDAO.getNumberOfAccounts(new User());



        accountDAO.getByCardNumber("1234123412341234");

        accountDAO.blockAccount(1L);

        accountDAO.unblockAccount(1L);

        Assert.assertFalse(accountDAO.getById(1L).isBlocked());
    }
}
