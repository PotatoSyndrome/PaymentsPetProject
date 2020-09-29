package org.epam.training.kocherhin;

import org.epam.training.kocherhin.DAO.*;
import org.epam.training.kocherhin.Entity.Admin;
import org.epam.training.kocherhin.Entity.Payment;
import org.epam.training.kocherhin.Entity.Template;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
//        System.out.println("Works");
//        java.util.Date dt = new java.util.Date();
//
//        java.text.SimpleDateFormat sdf =
//                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        String currentTime = sdf.format(dt);
//        System.out.println(currentTime);

        UserDAO userDAO = new UserDAO();
        AccountDAO accountDAO = new AccountDAO();
        PaymentDAO paymentDAO = new PaymentDAO();
        AdminDAO adminDAO = new AdminDAO();
        TemplateDAO templateDAO = new TemplateDAO();
        try {
//            System.out.println(userDAO.getByName("WORKS"));
//            System.out.println(accountDAO.getByUser(userDAO.getByName("WORKS").getId()));
//            System.out.println(paymentDAO.getByFromAccount(accountDAO.getByUser(userDAO.getByName("WORKS").getId()).get(0)));
//            System.out.println(paymentDAO.getByDestinationAccount(accountDAO.getByUser(userDAO.getByName("WORKS").getId()).get(1)));
//            System.out.println(adminDAO.getAdminByLogin("admin"));
            System.out.println(templateDAO.getTemplatesForUser(userDAO.getByName("WORKS")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
