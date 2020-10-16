package org.epam.training.kocherhin.Entity;

import org.junit.Assert;
import org.junit.Test;

public class AdminTest {

//    private String login;
//    private String password;

    @Test
    public void testEquals() {
        Admin expected = new Admin();
        Admin tested = new Admin();

        expected.setLogin("login");
        expected.setPassword("password");

        Assert.assertNotEquals(expected, tested);

        tested.setLogin("login");

        Assert.assertNotEquals(expected, tested);

        tested.setPassword("password");

        Assert.assertEquals(expected, tested);

        Assert.assertEquals(expected.toString(), tested.toString());

        Assert.assertEquals(expected.hashCode(), tested.hashCode());
    }
}
