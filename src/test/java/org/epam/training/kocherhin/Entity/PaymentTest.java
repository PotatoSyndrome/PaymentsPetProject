package org.epam.training.kocherhin.Entity;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Time;


public class PaymentTest {

//    private long from;
//    private String fromNumber;
//    private long to;
//    private String toNumber;
//    private int amount;
//    private Account.Currency currency;
//    private Payment.Status status;
//    private Date time;

    @Test
    public void testEquals() {
        Payment expected = new Payment();
        Payment tested = new Payment();

        expected.setFrom(2);
        expected.setFromNumber("1234123412341234");
        expected.setTo(3);
        expected.setToNumber("4321432143214321");
        expected.setAmount(5);
        expected.setCurrency("USD");
        expected.setStatus(Payment.Status.SENT);
        expected.setTime("2020-10-14 18:52:35");

        Assert.assertNotEquals(expected, tested);

        tested.setFrom(2);
        Assert.assertNotEquals(expected, tested);

        tested.setFromNumber("1234123412341234");
        Assert.assertNotEquals(expected, tested);

        tested.setTo(3);
        Assert.assertNotEquals(expected, tested);

        tested.setToNumber("4321432143214321");
        Assert.assertNotEquals(expected, tested);

        tested.setAmount(5);
        Assert.assertNotEquals(expected, tested);

        tested.setCurrency("USD");
        Assert.assertNotEquals(expected, tested);

        tested.setStatus(Payment.Status.SENT);
        Assert.assertNotEquals(expected, tested);

        tested.setTime("2020-10-14 18:52:35");
        Assert.assertEquals(expected, tested);

        Assert.assertEquals(expected.hashCode(), tested.hashCode());

        Assert.assertEquals(expected.toString(), tested.toString());
    }
}
