package org.epam.training.kocherhin.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class GeneralDAO<T> {

    private EntityMapper<T> mapper;

    public GeneralDAO(EntityMapper<T> mapper) {
        this.mapper = mapper;
    }

    public List<T> mapAll(PreparedSqlQuery query) throws SQLException {
        List<T> ts = new LinkedList<T>();
        try(Connection connection = ConnectionProvider.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query.getQuery())) {
            for (int i = 0; i < query.getParameters().length; ++i) {
                preparedStatement.setString(i + 1, escapeForLike(query.getParameters()[i]));
            }
            System.out.println(preparedStatement.toString());
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ts.add(mapper.mapObject(resultSet));
                }
            }
        }
        return ts;
    }

    public T mapOne(PreparedSqlQuery query) throws SQLException {
        T toReturn = null;
        try(Connection connection = ConnectionProvider.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query.getQuery())) {
            for (int i = 0; i < query.getParameters().length; ++i) {
                statement.setString(i + 1, escapeForLike(query.getParameters()[i]));
            }

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    toReturn = mapper.mapObject(resultSet);
                }
            }
        }
        return toReturn;
    }

    public void commitAll(PreparedSqlQuery... queries) throws SQLException {
        try(Connection connection = ConnectionProvider.getInstance().getConnection()) {
            connection.setAutoCommit(false);
            for (PreparedSqlQuery query: queries) {
                try(PreparedStatement statement = connection.prepareStatement(query.getQuery())) {
                    for (int i = 0; i < query.getParameters().length; ++i) {
                        statement.setString(i + 1, escapeForLike(query.getParameters()[i]));
                    }
                    statement.executeUpdate();
                }
            }
            connection.commit();
        }
    }

    public static String escapeForLike(String param) {
        return param.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");
    }

}
