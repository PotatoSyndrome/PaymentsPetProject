package org.epam.training.kocherhin.DAO;

import org.epam.training.kocherhin.Entity.User;
import org.epam.training.kocherhin.Web.ValidationUtil;

import javax.servlet.http.Cookie;
import java.sql.*;
import java.util.List;

public class UserDAO {

    private GeneralDAO<User> mapper;

    public UserDAO() {
        mapper = new GeneralDAO<>(new UserMapper());
    }

    public User getByName(String name) throws SQLException {
        return mapper.mapOne(new PreparedSqlQuery(
                Queries.GET_USER_BY_NAME, name));
    }

    public User getByCookies(Cookie[] cookies) throws SQLException { //todo change values
        User user;
        String login = null;
        String password = null;
        for (Cookie c: cookies) {
            if (c.getName().equals("lg")) {
                login = c.getValue();
            }
            if (c.getName().equals("pw")) {
                password = c.getValue();
            }
        }
        if (login == null || password == null) {
            return null;
        }
        user = mapper.mapOne(new PreparedSqlQuery(
                Queries.GET_USER_BY_NAME, login));
        if (user == null || !ValidationUtil.validateUser(user, password)) {
            return null;
        }
        return user;
    }

    public void addUser(User user) throws SQLException {
        mapper.commitAll(new PreparedSqlQuery(
                Queries.ADD_NEW_USER, user.getLogin(), user.getPassword(),
                user.isBlocked() ? "1" : "0"));
    }

    public List<User> getAllWithPagination(long page, long recordsPerPage) throws SQLException {
        return mapper.mapAll(new PreparedSqlQuery(
                Queries.GET_ALL_USERS_WITH_PAGINATION,
                (page - 1) * recordsPerPage,
                recordsPerPage));
    }

    public void blockUser(Long id) throws SQLException {
        mapper.commitAll(new PreparedSqlQuery(
            Queries.UN_BLOCK_USER,
                1,
                id
        ));
    }

    public void unblockUser(Long id) throws SQLException {
        mapper.commitAll(new PreparedSqlQuery(
                Queries.UN_BLOCK_USER,
                0,
                id
        ));
    }

    private static class UserMapper implements EntityMapper<User> {

        @Override
        public User mapObject(ResultSet resultSet) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setBlocked(resultSet.getBoolean("blocked"));
            return user;
        }
    }
}