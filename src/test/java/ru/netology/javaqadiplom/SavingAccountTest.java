package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SavingAccountTest {

    @Test
    public void ShouldCreateSavingAccount() { // создание сберегательного счета с валидными значениями
        SavingAccount account = new SavingAccount(1_000, 500, 5_000, 5);

        Assertions.assertEquals(5, account.getRate());
        Assertions.assertEquals(500, account.getMinBalance());
        Assertions.assertEquals(5_000, account.getMaxBalance());
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void ShouldCreateSavingAccountWithRateZero() { // создание сберегательного счета со ставкой 0
        SavingAccount account = new SavingAccount(
                1_000,
                500,
                5_000,
                0
        );

        Assertions.assertEquals(0, account.getRate());
        Assertions.assertEquals(1_000, account.getBalance());
        Assertions.assertEquals(500, account.getMinBalance());
        Assertions.assertEquals(5_000, account.getMaxBalance());

    }

    @Test
    public void ShouldCThrowsSavingAccountWithNegativeRate() { // создание сберегательного счета с негативной ставкой
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    1_000,
                    500,
                    5_000,
                    -1
            );

        });
    }

    @Test
    public void ShouldThrowsSavingAccountWithMinBalanceOverMaxAndInitial() { // создание сберегательного счета с минимальным балансом больше максимального и начального
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    1_000,
                    6_000,
                    5_000,
                    5
            );

        });
    }

    @Test
    public void ShouldThrowsSavingAccountWithInitialBalanceOverMaxBalance() {  //создание сберегательного счета со стартовым балансом больше максимального
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(
                    15_000,
                    2_000,
                    5_000,
                    5
            );

        });
    }

    @Test
    public void ShouldPaySuccessfully() { // успешная оплата
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(500);

        Assertions.assertEquals(1_500, account.getBalance());

    }

    @Test
    public void ShouldPaymentFailOverMinBalance() { // оплата не проходит по превышению минимального баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(1500);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void ShouldPaymentReturnFalseOverMinBalanse() { // завершение операции с false при превышении минимального баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertFalse(account.pay(1_500));
    }

    @Test
    public void ShouldPaymentFailOverNegativeBalance() { // оплата не проходит по причине отрицательнго баланса
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.pay(6_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void ShouldPaymentReturnFalseOverNegativeBalance() { // завершение операции с false при отрицательном балансе
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertFalse(account.pay(7_500));
    }

    @Test
    public void ShouldPaymentReturnTrueWithSuccessfullyOperation() { // Завершение операции с true при успешной оплате
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertTrue(account.pay(500));
    }

    @Test
    public void ShouldAddSuccessfully() { // успешное пополнение счета
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void ShouldReturnTrueThenAddSuccessfully() { // завершение операции с true при успешном пополнении
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertTrue(account.add(2_000));
    }

    @Test
    public void ShouldAddFailOverLimit() { // полнение на итоговую сумму, превышающую максимальный баланс

        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(12_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void ShouldReturnFalseForAddOverLimit() { // завершение операции с false при превышении максимального баланса

        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertFalse(account.add(12_000));
    }

    @Test
    public void ShouldСalculatePercents() { // посчитать проценты

        SavingAccount account = new SavingAccount(
                200,
                1_000,
                10_000,
                15
        );

        Assertions.assertEquals(30, account.yearChange());

    }

    @Test
    public void ShouldСalculatePercentsWithZeroBalance() { // рассчёт процентов c нулевым балансом

        SavingAccount account = new SavingAccount(
                0,
                1_000,
                10_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange());
    }
}



