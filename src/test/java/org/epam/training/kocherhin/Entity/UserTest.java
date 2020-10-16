package org.epam.training.kocherhin.Entity;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

//    private String login;
//    private String password;
//    private boolean blocked;

    @Test
    public void testEquals() {
        User expected = new User();
        User tested = new User();

        expected.setLogin("user@mail");
        expected.setPassword("password");
        expected.setBlocked(true);

        Assert.assertNotEquals(expected, tested);

        tested.setLogin("user@mail");
        Assert.assertNotEquals(expected, tested);

        tested.setPassword("password");
        Assert.assertNotEquals(expected, tested);

        tested.setBlocked(true);
        Assert.assertEquals(expected, tested);

        Assert.assertEquals(expected.hashCode(), tested.hashCode());

        Assert.assertEquals(expected.toString(), tested.toString());
    }
}
