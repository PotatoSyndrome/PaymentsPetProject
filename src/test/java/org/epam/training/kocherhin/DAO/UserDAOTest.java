package org.epam.training.kocherhin.DAO;

import org.junit.Test;

import java.sql.SQLException;

public class UserDAOTest {

    @Test
    public void tryAll() throws SQLException {
        UserDAO userDAO = new UserDAO();

        userDAO.blockUser(1L);

        userDAO.unblockUser(1L);

        userDAO.getByName("login1@mail");

        userDAO.getAllWithPagination(1,5);
    }
}
