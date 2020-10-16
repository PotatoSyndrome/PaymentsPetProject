package org.epam.training.kocherhin.DAO;

import org.epam.training.kocherhin.Entity.User;
import org.junit.Test;

import java.sql.SQLException;

public class TemplateDAOTest {

    @Test
    public void tryAll() throws SQLException {
        TemplateDAO templateDAO = new TemplateDAO();

        User user = new User();
        user.setId(1);
        templateDAO.getNumberOfTemplates(user);

        templateDAO.getTemplatesForUser(user);

        templateDAO.getTemplatesForUserWithPagination(user, 1, 1);
    }
}
