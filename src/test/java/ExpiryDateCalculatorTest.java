import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {
    // 서비스를 사용하려면 매달 1만 원을 선불로 납부한다. 납부일 기준으로 한 달 뒤가 서비스 만료일이 된다.
    // 2개월 이상 요금을 납부랑 수 있다.
    // 10만 원을 납부하면 서비스를 1년 제공한다.


    @Test
    void 만원_납부하면_한달_뒤가_만요일이_됨() {
        assertExpiryDate(PayDate.builder().billingDate(LocalDate.of(2019, 3, 1)).payAmount(10_000).build(), LocalDate.of(2019, 4, 1));
        assertExpiryDate(PayDate.builder().billingDate(LocalDate.of(2019, 5, 5)).payAmount(10_000).build(), LocalDate.of(2019, 6, 5));

    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(PayDate.builder().billingDate(LocalDate.of(2019, 1, 31)).payAmount(10_000).build(), LocalDate.of(2019, 2, 28));
        assertExpiryDate(PayDate.builder().billingDate(LocalDate.of(2019, 5, 31)).payAmount(10_000).build(), LocalDate.of(2019, 6, 30));
        assertExpiryDate(PayDate.builder().billingDate(LocalDate.of(2020, 1, 31)).payAmount(10_000).build(), LocalDate.of(2020, 2, 29));

    }

    private void assertExpiryDate(PayDate payDate, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payDate);
        assertEquals(expectedExpiryDate, realExpiryDate);

    }

}
