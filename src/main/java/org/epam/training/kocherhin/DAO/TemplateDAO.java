package org.epam.training.kocherhin.DAO;

import org.epam.training.kocherhin.Entity.Template;
import org.epam.training.kocherhin.Entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TemplateDAO {

    private GeneralDAO<Template> mapper;

    public TemplateDAO() {
        mapper = new GeneralDAO<>(new TemplateMapper());
    }

    public List<Template> getTemplatesForUser(User user) throws SQLException {
        return mapper.mapAll(new PreparedSqlQuery(
                Queries.GET_TEMPLATES_FOR_USER,Long.toString(user.getId())));
    }

    public List<Template> getTemplatesForUserWithPagination(User user, int page, int accountsOnPage) throws SQLException {
        return mapper.mapAll(new PreparedSqlQuery(
            Queries.GET_TEMPLATES_FOR_USER_WITH_PAGINATION,
                user.getId(),
                (page - 1) * accountsOnPage,
                accountsOnPage));
    }

    public int getNumberOfTemplates(User user) throws SQLException {
        return getTemplatesForUser(user).size();
    }

    public void insertTemplate(Template template) throws SQLException {
        mapper.commitAll(new PreparedSqlQuery(
                Queries.INSERT_NEW_TEMPLATE,
                template.getFrom(),
                template.getTo(),
                template.getAmount()
        ));
    }

    private static class TemplateMapper implements EntityMapper<Template> {

        @Override
        public Template mapObject(ResultSet resultSet) throws SQLException {
            Template template = new Template();

            template.setId(resultSet.getLong("id"));
            template.setFrom(resultSet.getLong("from_account"));
            template.setFromNumber(resultSet.getString("from_number"));
            template.setTo(resultSet.getLong("to_account"));
            template.setToNumber(resultSet.getString("to_number"));
            template.setAmount(resultSet.getInt("amount"));
            template.setCurrency(resultSet.getString("currency"));
            return template;
        }
    }
}
