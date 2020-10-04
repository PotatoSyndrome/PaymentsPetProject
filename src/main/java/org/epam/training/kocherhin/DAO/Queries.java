package org.epam.training.kocherhin.DAO;

public final class Queries {
    private Queries() {
    }

    public static final String GET_USER_BY_NAME = "SELECT * FROM users WHERE login = ?";

    public static final String GET_ACCOUNTS_BY_USER = "SELECT * FROM accounts\n" +
            "WHERE user_id = ?";

    public static final String GET_PAYMENTS_BY_FROM_ACCOUNT = "SELECT * FROM payments\n" +
            "WHERE from_account = ?";

    public static final String GET_PAYMENTS_BY_TO_ACCOUNT = "SELECT * FROM payments\n" +
            "WHERE to_account = ?";

    public static final String GET_ADMIN_BY_LOGIN = "SELECT * FROM mydb.admins\n" +
            "WHERE login = ?";

    public static final String GET_TEMPLATES_FOR_USER = "SELECT * FROM mydb.templates\n" +
            "WHERE from_account IN (\n" +
            "SELECT id from accounts WHERE user_id = ?)";

    public static final String ADD_NEW_USER = "INSERT INTO users(login, password, blocked) VALUES (?, ?, ?)";
}
