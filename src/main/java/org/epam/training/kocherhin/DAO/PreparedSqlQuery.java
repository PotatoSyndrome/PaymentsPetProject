package org.epam.training.kocherhin.DAO;

public class PreparedSqlQuery {

    private String query;
    private String[] parameters;

    public PreparedSqlQuery(String query, String... parameters) {
        this.query = query;
        this.parameters = parameters;
    }

    public String getQuery() {
        return query;
    }

    public String[] getParameters() {
        return parameters;
    }
}
