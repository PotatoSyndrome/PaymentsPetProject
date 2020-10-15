package org.epam.training.kocherhin.DAO;

public final class Queries {
    private Queries() {
    }

    public static final String GET_USER_BY_NAME = "SELECT * FROM users WHERE login = ?";

    public static final String GET_ALL_USERS_WITH_PAGINATION = "SELECT * FROM mydb.users\n" +
            "LIMIT ?, ?";

    public static final String GET_ACCOUNTS_BY_USER = "SELECT * FROM accounts\n" +
            "WHERE user_id = ?";

    public static final String UN_BLOCK_USER = "UPDATE users SET blocked = ? WHERE (id = ?)";

    public static final String GET_ACCOUNTS_BY_USER_WITH_PAGINATION = "SELECT * FROM accounts\n" +
            "WHERE user_id = ? LIMIT ?, ?";

    public static final String GET_ACCOUNT_BY_ID = "SELECT * FROM mydb.accounts\n" +
            "WHERE id = ?";

    public static final String GET_ACCOUNT_BY_CARD_NUMBER = "SELECT * FROM mydb.accounts\n" +
            "WHERE card_number = ?";

    public static final String GET_NUMBER_OF_ACCOUNTS = "SELECT COUNT(*) AS number FROM accounts WHERE user_id = ?";

    public static final String INSERT_ACCOUNT = "INSERT INTO accounts(card_number, name, amount, currency, user_id) \n" +
            "VALUES (?, ?, ?, ?, ?)";

    public static final String BLOCK_ACCOUNT = "UPDATE accounts SET blocked = 1 WHERE (id = ?)";

    public static final String UNBLOCK_ACCOUNT = "UPDATE accounts SET blocked = 0 WHERE (id = ?)";

    public static final String CHANGE_AMOUNT_IN_ACCOUNT = "UPDATE accounts SET amount = amount + ? WHERE id = ?";

    public static final String GET_PAYMENT_BY_ID = "SELECT p.id, p.from_account, fr.card_number AS from_number," +
            " p.to_account, t.card_number AS to_number, p.amount, fr.currency, p.status, p.time FROM mydb.payments p\n" +
            "JOIN accounts as fr ON from_account = fr.id\n" +
            "JOIN accounts as t ON to_account = t.id\n" +
            "WHERE p.id = ?";

    public static final String GET_PAYMENTS_BY_FROM_ACCOUNT = "SELECT p.id, p.from_account, fr.card_number AS from_number," +
            " p.to_account, t.card_number AS to_number, p.amount, fr.currency, p.status, p.time FROM mydb.payments p\n" +
            "JOIN accounts as fr ON from_account = fr.id\n" +
            "JOIN accounts as t ON to_account = t.id\n" +
            "WHERE p.from_account = ?";

    public static final String GET_PAYMENTS_BY_TO_ACCOUNT = "SELECT p.id, p.from_account, fr.card_number AS from_number," +
            " p.to_account, t.card_number AS to_number, p.amount, fr.currency, p.status, p.time FROM mydb.payments p\n" +
            "JOIN accounts as fr ON from_account = fr.id\n" +
            "JOIN accounts as t ON to_account = t.id\n" +
            "WHERE p.to_account = ?";

    public static final String GET_PAYMENTS_BY_FROM_USER = "SELECT p.id, p.from_account, fr.card_number AS from_number," +
            " p.to_account, t.card_number AS to_number, p.amount, fr.currency, p.status, p.time FROM mydb.payments p\n" +
            "JOIN accounts as fr ON from_account = fr.id\n" +
            "JOIN accounts as t ON to_account = t.id\n" +
            "WHERE p.from_account IN\n" +
            "(SELECT id FROM accounts WHERE user_id = ?)";

    public static final String GET_PAYMENTS_BY_FROM_USER_WITH_PAGINATION = "SELECT p.id, p.from_account, fr.card_number AS from_number,\n" +
            " p.to_account, t.card_number AS to_number, p.amount, fr.currency, p.status, p.time FROM mydb.payments p\n" +
            "JOIN accounts as fr ON from_account = fr.id\n" +
            "JOIN accounts as t ON to_account = t.id\n" +
            "WHERE p.from_account IN\n" +
            "(SELECT id FROM accounts WHERE user_id = ?)\n" +
            " LIMIT ?, ?";


    public static final String INSERT_PAYMENT = "INSERT INTO payments(from_account, to_account, amount, status, time) \n" +
            "VALUES (?, ?, ?, 'READY', now())";

    public static final String GET_READY_PAYMENTS = "SELECT p.id, p.from_account, fr.card_number AS from_number," +
            " p.to_account, t.card_number AS to_number, p.amount, fr.currency, p.status, p.time FROM mydb.payments p\n" +
            "JOIN accounts as fr ON from_account = fr.id\n" +
            "JOIN accounts as t ON to_account = t.id\n" +
            "WHERE status = 'ready'";

    public static final String GET_READY_PAYMENTS_WITH_PAGINATION = "SELECT p.id, p.from_account, fr.card_number AS from_number," +
            " p.to_account, t.card_number AS to_number, p.amount, fr.currency, p.status, p.time FROM mydb.payments p\n" +
            "JOIN accounts as fr ON from_account = fr.id\n" +
            "JOIN accounts as t ON to_account = t.id\n" +
            "WHERE status = 'ready' LIMIT ?, ?";

    public static final String DECLINE_PAYMENT = "UPDATE payments SET status = 'DECLINED' WHERE (id = ?)";

    public static final String DECLINE_ALL_READY_PAYMENTS = "UPDATE payments SET status = \"DECLINED\" WHERE from_account = ? AND status = \"READY\"";

    public static final String SEND_PAYMENT = "UPDATE payments SET status = 'SENT' WHERE (id = ?)";

    public static final String GET_ADMIN_BY_LOGIN = "SELECT * FROM mydb.admins\n" +
            "WHERE login = ?";

    public static final String GET_TEMPLATES_FOR_USER = "SELECT tp.id, tp.from_account," +
            " fr.card_number AS from_number, tp.to_account, t.card_number AS to_number, \n" +
            "tp.amount, fr.currency  FROM mydb.templates AS tp\n" +
            "JOIN accounts as fr ON from_account = fr.id\n" +
            "JOIN accounts as t ON to_account = t.id\n" +
            "WHERE tp.from_account IN (\n" +
            "SELECT id from accounts WHERE user_id = ?)";

    public static final String GET_TEMPLATES_FOR_USER_WITH_PAGINATION = "SELECT tp.id, tp.from_account, " +
            "fr.card_number AS from_number, tp.to_account, t.card_number AS to_number, \n" +
            "tp.amount, fr.currency  FROM mydb.templates AS tp\n" +
            "JOIN accounts as fr ON from_account = fr.id\n" +
            "JOIN accounts as t ON to_account = t.id\n" +
            "WHERE tp.from_account IN (\n" +
            "SELECT id from accounts WHERE user_id = ?)\n" +
            "LIMIT ?, ?";

    public static final String INSERT_NEW_TEMPLATE = "INSERT INTO templates(from_account, to_account, amount)\n" +
            "VALUES (?, ?, ?)";

    public static final String ADD_NEW_USER = "INSERT INTO users(login, password, blocked) VALUES (?, ?, ?)";

    public static final String GET_UNCONSIDERED_REQUESTS_WITH_PAGINATION = "SELECT a.*, changed_by, u.login, considered FROM mydb.unblock_request\n" +
            "JOIN accounts AS a ON accounts_id = a.id\n" +
            "JOIN users AS u ON a.user_id = u.id\n" +
            "WHERE considered = 0\n" +
            "LIMIT ?, ?";

    public static final String GET_UNCONSIDERED_REQUESTS = "SELECT a.*, changed_by, u.login, considered FROM mydb.unblock_request\n" +
            "JOIN accounts AS a ON accounts_id = a.id\n" +
            "JOIN users AS u ON a.user_id = u.id\n" +
            "WHERE considered = 0\n";

    public static final String DELETE_OLD_REQUEST = "DELETE FROM unblock_request WHERE accounts_id = ?";

    public static final String ADD_REQUEST = "INSERT INTO unblock_request(accounts_id, considered) VALUES(?, 0)";

    public static final String CONSIDER_REQUEST = "UPDATE unblock_request SET changed_by = ?, considered = 1 WHERE (accounts_id = ?);\n";
}
