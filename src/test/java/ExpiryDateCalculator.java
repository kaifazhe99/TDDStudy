import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayDate payDate) {
        int addedMonths = payDate.getPayAmount() == 100_000 ? 12 : payDate.getPayAmount() / 10_000;
        if (payDate.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payDate, addedMonths);
        } else {
            return payDate.getBillingDate().plusMonths(addedMonths);
        }

    }


    private LocalDate expiryDateUsingFirstBillingDate(PayDate payDate, int addedMonths) {
        LocalDate candidateExp = payDate.getBillingDate().plusMonths(addedMonths);
        final int dayOfFirstBilling = payDate.getFirstBillingDate().getDayOfMonth();
        if (dayOfFirstBilling != candidateExp.getDayOfMonth()) {
            final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();
            if (dayLenOfCandiMon <
                    payDate.getFirstBillingDate().getDayOfMonth()) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else {
            return candidateExp;
        }

    }
}
