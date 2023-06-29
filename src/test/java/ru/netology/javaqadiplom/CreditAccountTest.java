package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToEmptyBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                2_000,
                4_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void shouldAddNothingToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                2_000,
                3_000,
                15
        );

        account.add(0);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void paymentShouldBeSuccessful() {
        CreditAccount account = new CreditAccount(
                4_000,
                15_000,
                12
        );

        account.pay(2_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void paymentShouldBeSuccessfulWithEmptyBalance() {
        CreditAccount account = new CreditAccount(
                0,
                15_000,
                12
        );

        account.pay(2_000);

        Assertions.assertEquals(-2_000, account.getBalance());
    }

    @Test
    public void paymentShouldNotBeSuccessful() {
        CreditAccount account = new CreditAccount(
                5_000,
                15_000,
                12
        );

        account.pay(25_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void paymentShouldBeSuccessfulWithEmptyAmount() {
        CreditAccount account = new CreditAccount(
                15_000,
                15_000,
                12
        );

        account.pay(0);

        Assertions.assertEquals(15_000, account.getBalance());
    }

    @Test
    public void paymentShouldBeSuccessfulIfPayAllLimit() {
        CreditAccount account = new CreditAccount(
                15_000,
                15_000,
                12
        );

        account.pay(30_000);

        Assertions.assertEquals(-15_000, account.getBalance());
    }

    @Test
    public void shouldGetCreditLimit() {
        CreditAccount account = new CreditAccount(
                15_000,
                15_000,
                12
        );

        Assertions.assertEquals(15_000, account.getCreditLimit());
    }

    @Test
    public void shouldGetEmptyCreditLimit() {
        CreditAccount account = new CreditAccount(
                15_000,
                0,
                12
        );

        Assertions.assertEquals(0, account.getCreditLimit());
    }

    @Test
    public void shouldGetCreditLimitWithEmptyRate() {
        CreditAccount account = new CreditAccount(
                15_000,
                15_000,
                0
        );

        Assertions.assertEquals(15_000, account.getCreditLimit());
    }

    @Test
    public void shouldNotGetNegativeInitialBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(-15_000, 15_000, 12);
        });
    }

    @Test
    public void shouldNotGetNegativeLimit() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(15_000, -15_000, 12);
        });
    }

    @Test
    public void shouldNotGetNegativeRate() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(15_000, 15_000, -12);
        });
    }

    @Test
    public void shouldCalculateRate() {
        CreditAccount account = new CreditAccount(
                15_000,
                15_000,
                15
        );

        account.pay(30_000);

        Assertions.assertEquals(-2_250, account.yearChange());
    }

    @Test
    public void shouldCalculateRateWithOtherRate() {
        CreditAccount account = new CreditAccount(
                13_250,
                15_000,
                13
        );

        account.pay(14_500);

        Assertions.assertEquals(-162, account.yearChange());
    }

    @Test
    public void shouldNotCalculateRateWithPositiveBalance() {
        CreditAccount account = new CreditAccount(
                15,
                15_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    public void shouldNotCalculateRateWithNullRate() {
        CreditAccount account = new CreditAccount(
                15,
                15_000,
                0
        );

        account.pay(4_000);

        Assertions.assertEquals(0, account.yearChange());
    }
}
