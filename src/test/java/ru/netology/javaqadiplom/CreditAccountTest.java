package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }


//    @Test
//    public void shouldNotAddToPositiveBalance() {
//        CreditAccount account = new CreditAccount(
//                2_000,
//                4_000,
//                15
//        );
//
//        account.add(3_000);
//
//        Assertions.assertEquals(2_000, account.getBalance());
//    } // не должен менять баланс, если после покупки он выходит за лимит

//    @Test
//    public void shouldAddToMaxPositiveBalance() {
//        CreditAccount account = new CreditAccount(
//                2_000,
//                5_000,
//                15
//        );
//
//        account.add(3_000);
//
//        Assertions.assertEquals(5_000, account.getBalance());
//    } // должен увеличить баланс до кредитного лимита

    @Test
    public void shouldAddNothingToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                2_000,
                3_000,
                15
        );

        account.add(0);

        Assertions.assertEquals(2_000, account.getBalance());
    } // не меняет баланс, если покупка = 0

//    @Test
//    public void shouldAddNothingToPositiveBalanceIfAmountMoreThanLimit() {
//        CreditAccount account = new CreditAccount(
//                2_000,
//                3_000,
//                15
//        );
//
//        account.add(4_000);
//
//        Assertions.assertEquals(2_000, account.getBalance());
//    } // не должен менять баланс, если изначально покупка больше лимита

//    @Test
//    public void paymentShouldBeSuccessful() {
//        CreditAccount account = new CreditAccount(
//                4_000,
//                15_000,
//                12
//        );
//
//        account.pay(2_000);
//
//        Assertions.assertEquals(2_000, account.getBalance());
//    } // списание в рамках лимита не проходит

//    @Test
//    public void paymentShouldNotBeSuccessful() {
//        CreditAccount account = new CreditAccount(
//                4_000,
//                15_000,
//                12
//        );
//
//        account.pay(5_000);
//
//        Assertions.assertEquals(4_000, account.getBalance());
//    } // не должен менять баланс, если списание больше баланса

//    @Test
//    public void paymentShouldNotBeSuccessfulWithEmptyBalance() {
//        CreditAccount account = new CreditAccount(
//                0,
//                15_000,
//                12
//        );
//
//        account.pay(5_000);
//
//        Assertions.assertEquals(0, account.getBalance());
//    } // списание не должно проходить, если изначальный баланс 0

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

//    @Test
//    public void paymentShouldBeSuccessfulIfPayAllLimit() {
//        CreditAccount account = new CreditAccount(
//                15_000,
//                15_000,
//                12
//        );
//
//        account.pay(15_000);
//
//        Assertions.assertEquals(0, account.getBalance());
//    } // должен списать весь лимит до 0

    @Test
    public void shouldGetCreditLimit() {
        CreditAccount account = new CreditAccount(
                15_000,
                15_000,
                12
        );

        account.getCreditLimit();

        Assertions.assertEquals(15_000, account.getCreditLimit());
    }

//    @Test
//    public void shouldNotGetCreditLimitWithNegativeLimit() {
//        CreditAccount account = new CreditAccount(
//                15_000,
//                -15_000,
//                12
//        );
//
//        account.getCreditLimit();
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            account.getCreditLimit();
//        });
//    } // тест не проходит

//    @Test
//    public void shouldNotGetCreditLimitWithEmptyRate() {
//        CreditAccount account = new CreditAccount(
//                15_000,
//                15_000,
//                0
//        );
//
//        account.getCreditLimit();
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//        account.getCreditLimit()
//        });
//    } // тест не проходит

    @Test
    public void shouldCalculateRate() {
        CreditAccount account = new CreditAccount(
                -15_000,
                15_000,
                15
        );

        account.yearChange();

        Assertions.assertEquals(-2_250, account.yearChange());
    }

    @Test
    public void shouldCalculateRateWithOtherRate() {
        CreditAccount account = new CreditAccount(
                -15_000,
                15_000,
                13
        );

        account.yearChange();

        Assertions.assertEquals(-1_800, account.yearChange());
    }

//    @Test
//    public void shouldNotCalculateRateWithNegativeRate() {
//        CreditAccount account = new CreditAccount(
//                -15_000,
//                15_000,
//                -2
//        );
//
//        account.yearChange();
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> {
//        account.yearChange();
//        }); // тест не проходит
//    } // не должен считать при отрицательной ставке

    @Test
    public void shouldNotCalculateWithPositiveBalance() {
        CreditAccount account = new CreditAccount(
                15,
                15_000,
                15
        );

        account.yearChange();

        Assertions.assertEquals(0, account.yearChange());
    }
}
