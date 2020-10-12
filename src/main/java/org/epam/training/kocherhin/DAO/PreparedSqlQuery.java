package org.epam.training.kocherhin.DAO;

public class PreparedSqlQuery {

    private String query;
    private Object[] parameters;

    public PreparedSqlQuery(String query, Object... parameters) {
        this.query = query;
        this.parameters = parameters;
    }

    public String getQuery() {
        return query;
    }

    public Object[] getParameters() {
        return parameters;
    }
}
