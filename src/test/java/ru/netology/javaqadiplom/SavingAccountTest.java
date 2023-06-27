package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    private int initialBalance = 3_000;
    private int minBalance = 2_000;
    private int maxBalance = 10_000;
    private int rate = 5;

    /**
     * МЕТОДЫ ИНИЦИАЦИИ СЧЕТА
     */

    @Test   // Что ему дали, то и получили от геттеров проигнорировав IllegalArgumentException если таковые будут
    public void initialBalance() {
        try {
            SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

            int[] expected = {initialBalance, minBalance, maxBalance, rate};
            int[] actual = {account.getBalance(), account.getMinBalance(), account.getMaxBalance(), account.getRate()};
            Assertions.assertArrayEquals(expected, actual);
        } catch (IllegalArgumentException msg) {
            System.out.println(msg.getMessage());
        }
    }   // Инициализация параметров счёта с отключенными исключениями (за счёт try/catch)

    /**
     * Версия с assertThrows - отлова любых IllegalArgumentException
     * Эмуляция неверных параметров счета для срабатывания исключений при инициации
     * начнет работать после реализации IllegalArgumentException в коде
     */

    @Test
    public void initialExceptionNegativeBalance() {
        minBalance = 0;
        initialBalance = -2;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {

            SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);
        });

    }   // Попытка создать отрицательный баланс

    @Test
    public void initialExceptionNegativeRate() {
        rate = -1;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {

            SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);
        });

    }   // Попытка создать отрицательную процентную ставку

    @Test
    public void initialExceptionLessMinBalance() {
        initialBalance = minBalance - 2;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {

            SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);
        });

    }   // Попытка создать баланс меньше минимального

    @Test
    public void initialExceptionMoreMaxBalance() {
        initialBalance = maxBalance + 2;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {

            SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);
        });

    }   // Попытка создать баланс больше максимального

    @Test
    public void initialExceptionMinMoreMaxBalance() {
        minBalance = maxBalance + 2;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {

            SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);
        });

    }   // Попытка создать минимальный больше максимального

    @Test
    public void initialExceptionMaxLessMinBalance() {
        maxBalance = minBalance - 2;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {

            SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);
        });

    }   // Попытка создать максимальный меньше минимального

    /**
     * ОСТАЛЬНЫЕ МЕТОДЫ
     * значения переменных расчитываются сами, с дополнительной корретировкой ошибок
     * которые могут возникнуть при вводе параметров счета в заголовке тестов
     * !!! руками без нужды не лазить java сама всё посчитат !!!
     */

    @Test
    public void addAmount() {
        if (initialBalance >= maxBalance) maxBalance = initialBalance + 1_000;
        int digit = 1;

        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        int expected = account.getBalance() + digit;
        account.add(digit);

        Assertions.assertEquals(expected, account.getBalance());
    }   // Увеличение баланса счета

    @Test
    public void payAmount() {
        if (initialBalance <= minBalance) initialBalance = minBalance + 1_000;

        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        int expected = account.getBalance() - 1;
        account.pay(1);

        Assertions.assertEquals(expected, account.getBalance());
    }   // Уменьшение баланса счета

    @Test
    public void payAmountMoreMinBalance() {
        if (initialBalance <= minBalance) initialBalance = minBalance + 1_000;

        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        int digit = initialBalance - minBalance + 1;
        boolean expected = false;

        Assertions.assertEquals(expected, account.pay(digit));
    }   // Нижняя граница минимального баланса

    @Test
    public void payAmountMinBalance() {
        if (initialBalance <= minBalance) initialBalance = minBalance + 1_000;

        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        int digit = initialBalance - minBalance;
        boolean expected = true;

        Assertions.assertEquals(expected, account.pay(digit));
    }   // Граница минимального баланса

    @Test
    public void payAmountLessMinBalance() {
        if (initialBalance <= minBalance) initialBalance = minBalance + 1_000;

        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        int digit = initialBalance - minBalance - 1;
        boolean expected = true;

        Assertions.assertEquals(expected, account.pay(digit));
    }   // Верхняя граница минимального баланса

    @Test
    public void addAmountLessMaxBalance() {
        if (initialBalance >= maxBalance) maxBalance = initialBalance + 1_000;

        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        int digit = maxBalance - initialBalance - 1;
        boolean expected = true;

        Assertions.assertEquals(expected, account.add(digit));
    }   // Нижняя граница максимального баланса

    @Test
    public void addAmountMaxBalance() {
        if (initialBalance >= maxBalance) maxBalance = initialBalance + 1_000;

        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        int digit = maxBalance - initialBalance;
        boolean expected = true;

        Assertions.assertEquals(expected, account.add(digit));
    }   // Граница максимального баланса

    @Test
    public void addAmountMoreMaxBalance() {
        if (initialBalance >= maxBalance) maxBalance = initialBalance + 1_000;

        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        int digit = maxBalance - initialBalance + 1;
        boolean expected = false;

        Assertions.assertEquals(expected, account.add(digit));
    }   // Верхняя граница максимального баланса

    @Test
    public void yearPercent() {
        if (minBalance == maxBalance || minBalance > maxBalance || (maxBalance - minBalance) < 1000) {
            minBalance = 0;
            maxBalance = 10_000;
            initialBalance = 5128;
        } else {
            initialBalance = (maxBalance - minBalance) / 2 + 128;
        }

        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        int expected = (int) ((double) account.getBalance() / 100 * rate);

        Assertions.assertEquals(expected, account.yearChange());
    }   // Точность расчета процентов
}
