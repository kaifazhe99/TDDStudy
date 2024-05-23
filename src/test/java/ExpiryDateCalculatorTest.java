import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {
    // 서비스를 사용하려면 매달 1만 원을 선불로 납부한다. 납부일 기준으로 한 달 뒤가 서비스 만료일이 된다.
    // 2개월 이상 요금을 납부랑 수 있다.
    // 10만 원을 납부하면 서비스를 1년 제공한다.


    @Test
    void 만원_납부하면_한달_뒤가_만요일이_됨() {
        LocalDate billingDate = LocalDate.of(2019, 3, 1);
        int payAmount = 10_000;

        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);

        assertEquals(LocalDate.of(2019, 4, 1), expiryDate);

        LocalDate billingDate2 = LocalDate.of(2019, 5, 5);
        int payAmount2 = 10_000;

        ExpiryDateCalculator cal2 = new ExpiryDateCalculator();
        LocalDate expiryDate2 = cal.calculateExpiryDate(billingDate2, payAmount2);

        assertEquals(LocalDate.of(2019, 6, 5), expiryDate2);

    }

}
