package org.epam.training.kocherhin.DAO;

import org.epam.training.kocherhin.Entity.Admin;
import org.epam.training.kocherhin.Entity.User;
import org.epam.training.kocherhin.Web.ValidationUtil;

import javax.servlet.http.Cookie;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    GeneralDAO<Admin> mapper;

    public AdminDAO() {
        mapper = new GeneralDAO<>(new AdminMapper());
    }

    public Admin getAdminByLogin(String name) throws SQLException {
        return mapper.mapOne(new PreparedSqlQuery(
                Queries.GET_ADMIN_BY_LOGIN, name));
    }

    public Admin getByCookies(Cookie[] cookies) throws SQLException {
        Admin admin;
        String login = null;
        String password = null;
        for (Cookie c: cookies) {
            if (c.getName().equals("adlg")) {
                login = c.getValue();
            }
            if (c.getName().equals("adpw")) {
                password = c.getValue();
            }
        }
        if (login == null || password == null) {
            return null;
        }
        admin = getAdminByLogin(login);
        if (admin == null || !ValidationUtil.validateAdmin(admin, password)) {
            return null;
        }

        return null;
    }

    private static class AdminMapper implements EntityMapper<Admin> {

        @Override
        public Admin mapObject(ResultSet resultSet) throws SQLException {
            Admin admin = new Admin();

            admin.setId(resultSet.getLong("id"));
            admin.setLogin(resultSet.getString("login"));
            admin.setPassword(resultSet.getString("password"));

            return admin;
        }
    }
}
