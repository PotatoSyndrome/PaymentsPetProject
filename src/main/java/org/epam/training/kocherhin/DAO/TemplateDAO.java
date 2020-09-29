package org.epam.training.kocherhin.DAO;

import org.epam.training.kocherhin.Entity.Template;
import org.epam.training.kocherhin.Entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TemplateDAO {

    GeneralMapper<Template> mapper;

    public TemplateDAO() {
        mapper = new GeneralMapper<>(new TemplateMapper());
    }

    public List<Template> getTemplatesForUser(User user) throws SQLException {
        return mapper.mapAll(Queries.GET_TEMPLATES_FOR_USER,Long.toString(user.getId()));
    }

    private static class TemplateMapper implements GeneralMapper.Mapper<Template> {

        @Override
        public Template mapObject(ResultSet resultSet) throws SQLException {
            Template template = new Template();

            template.setId(resultSet.getLong("id"));
            template.setFrom(resultSet.getLong("from_account"));
            template.setTo(resultSet.getLong("to_account"));
            template.setAmount(resultSet.getInt("amount"));

            return template;
        }
    }
}
