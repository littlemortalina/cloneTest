package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldNotAddZeroAmount() {
        CreditAccount account = new CreditAccount(1000, 5000, 15);

        boolean result = account.add(0);

        Assertions.assertFalse(result);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void shouldAddMultipleTimes() {
        CreditAccount account = new CreditAccount(1000, 5000, 15);

        account.add(500);
        account.add(300);

        Assertions.assertEquals(1800, account.getBalance());
    }
}
