package org.epam.training.kocherhin.DAO;

import org.epam.training.kocherhin.Entity.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    GeneralMapper<Admin> mapper;

    public AdminDAO() {
        mapper = new GeneralMapper<>(new AdminMapper());
    }

    public Admin getAdminByLogin(String name) throws SQLException {
        return mapper.mapOne(Queries.GET_ADMIN_BY_LOGIN, name);
    }

    private static class AdminMapper implements GeneralMapper.Mapper<Admin> {



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
