package org.epam.training.kocherhin.DAO;

import org.epam.training.kocherhin.Entity.User;

import java.sql.*;

public class UserDAO {

    private GeneralDAO<User> mapper;

    public UserDAO() {
        mapper = new GeneralDAO<>(new UserMapper());
    }

    public User getByName(String name) throws SQLException {
        return mapper.mapOne(new PreparedSqlQuery(
                Queries.GET_USER_BY_NAME, name));
    }

    public void addUser(User user) throws SQLException {
        mapper.commitAll(new PreparedSqlQuery(
                Queries.ADD_NEW_USER, user.getLogin(), user.getPassword(),
                user.isBlocked() ? "1" : "0"));
    }

    private static class UserMapper implements EntityMapper<User> {

        @Override
        public User mapObject(ResultSet resultSet) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword("password");
            user.setBlocked(resultSet.getBoolean("blocked"));
            return user;
        }
    }
}