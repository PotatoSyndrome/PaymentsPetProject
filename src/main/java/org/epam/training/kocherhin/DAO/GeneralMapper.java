package org.epam.training.kocherhin.DAO;

import org.epam.training.kocherhin.Entity.Payment;
import org.epam.training.kocherhin.Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class GeneralMapper<T> {

    private Mapper<T> mapper;

    public GeneralMapper(Mapper<T> mapper) {
        this.mapper = mapper;
    }

    public List<T> mapAll(String query, String... args) throws SQLException {
        List<T> ts = new LinkedList<T>();
        try(Connection connection = ConnectionProvider.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < args.length; ++i) {
                preparedStatement.setString(i + 1, escapeForLike(args[i]));
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

    public T mapOne(String query, String... args) throws SQLException {
        T toReturn = null;
        try(Connection connection = ConnectionProvider.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < args.length; ++i) {
                statement.setString(i + 1, escapeForLike(args[i]));
            }

            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    toReturn = mapper.mapObject(resultSet);
                }
            }
        }
        return toReturn;
    }

    public static String escapeForLike(String param) {
        return param.replace("!", "!!").replace("%", "!%").replace("_", "!_").replace("[", "![");
    }

    @FunctionalInterface
    interface Mapper<M> {
        public M mapObject(ResultSet resultSet) throws SQLException;
    }
}
