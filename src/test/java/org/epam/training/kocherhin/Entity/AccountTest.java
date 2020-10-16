package org.epam.training.kocherhin.Entity;

import org.junit.Assert;
import org.junit.Test;

public class AccountTest {

//    private String cardNumber;
//    private String name;
//    private int amount;
//    private Account.Currency currency;
//    private long userId;
//    private boolean blocked;

    @Test
    public void testEquals() {
        Account expected = new Account();
        Account tested = new Account();

        expected.setCardNumber("1234567890123456");
        expected.setName("card");
        expected.setAmount(12);
        expected.setCurrency(Account.Currency.USD);
        expected.setUserId(3);
        expected.setBlocked(true);

        Assert.assertNotEquals(expected, tested);

        tested.setCardNumber("1234567890123456");

        Assert.assertNotEquals(expected, tested);

        tested.setName("card");

        Assert.assertNotEquals(expected, tested);

        tested.setAmount(12);

        Assert.assertNotEquals(expected, tested);

        tested.setCurrency(Account.Currency.USD);

        Assert.assertNotEquals(expected, tested);

        tested.setUserId(3);

        Assert.assertNotEquals(expected, tested);

        tested.setBlocked(true);

        Assert.assertEquals(expected, tested);

        Assert.assertEquals(expected.hashCode(), tested.hashCode());

        Assert.assertEquals(expected.toString(), tested.toString());
    }

    @Test
    public void currencyConversionTest() {
        int converted = Account.Currency.convert(Account.Currency.USD, Account.Currency.EUR, 100);

        Assert.assertEquals(86, converted);
    }
}
