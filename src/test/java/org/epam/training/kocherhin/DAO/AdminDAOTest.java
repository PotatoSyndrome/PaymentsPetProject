package org.epam.training.kocherhin.DAO;

import org.junit.Test;

import java.sql.SQLException;

public class AdminDAOTest {

    @Test
    public void tryAll() throws SQLException {
        AdminDAO adminDAO = new AdminDAO();

        adminDAO.getAdminByLogin("admin");
    }
}
