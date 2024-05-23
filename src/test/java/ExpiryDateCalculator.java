import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayDate payDate) {
        int addedMonths = payDate.getPayAmount() / 10_000;
        if (payDate.getFirstBillingDate() != null) {
            LocalDate candidateExp = payDate.getBillingDate().plusMonths(addedMonths);
            if (payDate.getFirstBillingDate().getDayOfMonth() != candidateExp.getDayOfMonth()) {
                if(YearMonth.from(candidateExp).lengthOfMonth()<
                payDate.getFirstBillingDate().getDayOfMonth()){
                    return candidateExp.withDayOfMonth(
                            YearMonth.from(candidateExp).lengthOfMonth()
                    );
                }
                return candidateExp.withDayOfMonth(payDate.getFirstBillingDate().getDayOfMonth());
            }
        }
        return payDate.getBillingDate().plusMonths(addedMonths);
    }
}
