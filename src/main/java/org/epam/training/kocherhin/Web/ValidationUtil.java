package org.epam.training.kocherhin.Web;

import org.epam.training.kocherhin.Entity.Account;
import org.epam.training.kocherhin.Entity.Admin;
import org.epam.training.kocherhin.Entity.User;

public final class ValidationUtil {
    private ValidationUtil() {}

    public static boolean validateEmail(String email) {
        return true; //todo
    }
    public static boolean validatePassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        return password.matches("\\w+");
    }

    public static boolean validateUser(User user, String password) {
        return user.getPassword().equals(password) && !user.isBlocked();
    }

    public static boolean validateAdmin(Admin admin, String password) {
        return admin.getPassword().equals(password);
    }

    public static boolean validateCardNumber(String number) {
        return number.matches("\\d{16}");
    }

    public static boolean validateDigits(String number) {
        return number.matches("\\d+");
    }

    public static boolean hasEnoughMoney(Account account, int amount) {
        return account.getAmount() >= amount;
    }
}
