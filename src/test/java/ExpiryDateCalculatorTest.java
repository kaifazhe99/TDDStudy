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

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부(){
        PayDate payData = PayDate.builder().firstBillingDate(LocalDate.of(2019,1,31)).billingDate(LocalDate.of(2019,2,28)).payAmount(10_000).build();
        assertExpiryDate(payData,LocalDate.of(2019,3,31));

    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산(){
        assertExpiryDate(
                PayDate.builder().billingDate(LocalDate.of(2019,3,1)).payAmount(20_000).build(),
                LocalDate.of(2019,5,1)
        );
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부(){
        assertExpiryDate(
                PayDate.builder().firstBillingDate(LocalDate.of(2019,1,31)).billingDate(LocalDate.of(2019,2,28)).payAmount(20_000).build(),
                LocalDate.of(2019,4,30)
        );
    }

    @Test
    void 십만원을_납부하면_1년_제공(){
        assertExpiryDate(
                PayDate.builder().billingDate(LocalDate.of(2019,1,28)).payAmount(100_000).build(),
                LocalDate.of(2020,1,28)
        );

    }


    private void assertExpiryDate(PayDate payDate, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payDate);
        assertEquals(expectedExpiryDate, realExpiryDate);

    }

}
