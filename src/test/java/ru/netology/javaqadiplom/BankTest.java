package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankTest {

    CreditAccount cred = new CreditAccount(1_000, 5_000, 15);
    SavingAccount save = new SavingAccount(1_000, 0, 5_000, 1);

    @Test
    public void shouldTransferToSaving() {
        Bank bank = new Bank();

        Assertions.assertEquals(true, bank.transfer(cred, save, 500));
    }

    @Test
    public void shouldNotTransferToSaving() {
        Bank bank = new Bank();

        Assertions.assertEquals(false, bank.transfer(cred, save, 5_000));
    }

    @Test
    public void shouldNotTransferToSavingWithNullAmount() {
        Bank bank = new Bank();

        Assertions.assertEquals(false, bank.transfer(cred, save, 0));
    }

//    @Test
//    public void shouldTransferToCredit() {
//        Bank bank = new Bank();
//
//        Assertions.assertEquals(true, bank.transfer(save, cred, 1_000));
//    }

    @Test
    public void shouldNotTransferToCredit() {
        Bank bank = new Bank();

        Assertions.assertEquals(false, bank.transfer(save, cred, 1_999));
    }

    @Test
    public void shouldNotTransferToCreditWithNullAmount() {
        Bank bank = new Bank();

        Assertions.assertEquals(false, bank.transfer(save, cred, 0));
    }
}
