package org.epam.training.kocherhin.Entity;

import org.junit.Assert;
import org.junit.Test;

public class TemplateTest {

//    private long from;
//    private String fromNumber;
//    private long to;
//    private String toNumber;
//    private int amount;
//    private Account.Currency currency;

    @Test
    public void testEquals() {
        Template expected = new Template();
        Template tested = new Template();

        expected.setFrom(3);
        expected.setFromNumber("1234123412341234");
        expected.setTo(4);
        expected.setToNumber("4321432143214321");
        expected.setAmount(2);
        expected.setCurrency("USD");

        Assert.assertNotEquals(expected, tested);

        tested.setFrom(3);
        Assert.assertNotEquals(expected, tested);

        tested.setFromNumber("1234123412341234");
        Assert.assertNotEquals(expected, tested);

        tested.setTo(4);
        Assert.assertNotEquals(expected, tested);

        tested.setToNumber("4321432143214321");
        Assert.assertNotEquals(expected, tested);

        tested.setAmount(2);
        Assert.assertNotEquals(expected, tested);

        tested.setCurrency("USD");
        Assert.assertEquals(expected, tested);

        Assert.assertEquals(expected.hashCode(), tested.hashCode());

        Assert.assertEquals(expected.toString(), tested.toString());
    }
}
