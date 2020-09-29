package org.epam.training.kocherhin.DAO;

import org.epam.training.kocherhin.Entity.User;

import java.sql.*;

public class UserDAO {

    private GeneralMapper<User> mapper;

    public UserDAO() {
        mapper = new GeneralMapper<>(new UserMapper());
    }

    public User getByName(String name) throws SQLException {
        return mapper.mapOne(Queries.GET_USER_BY_NAME, name);
    }

    private static class UserMapper implements GeneralMapper.Mapper<User> {

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
