package org.epam.training.kocherhin.Entity;

import org.junit.Assert;
import org.junit.Test;

public class UnblockRequestTest {
//    private Account account;
//    private Long changedBy;
//    private String userLogin;
//    private boolean considered;

    @Test
    public void testEquals() {
        UnblockRequest expected = new UnblockRequest();
        UnblockRequest tested = new UnblockRequest();

        expected.setAccount(new Account());
        expected.setChangedBy(3L);
        expected.setUserLogin("user@mail");
        expected.setConsidered(true);

        Assert.assertNotEquals(expected, tested);

        tested.setAccount(new Account());
        Assert.assertNotEquals(expected, tested);

        tested.setChangedBy(3L);
        Assert.assertNotEquals(expected, tested);

        tested.setUserLogin("user@mail");
        Assert.assertNotEquals(expected, tested);

        tested.setConsidered(true);
        Assert.assertEquals(expected, tested);

        Assert.assertEquals(expected.hashCode(), tested.hashCode());

        Assert.assertEquals(expected.toString(), tested.toString());
    }


}
