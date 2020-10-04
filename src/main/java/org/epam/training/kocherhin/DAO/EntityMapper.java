package org.epam.training.kocherhin.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface EntityMapper<M> {
    public M mapObject(ResultSet resultSet) throws SQLException;
}
