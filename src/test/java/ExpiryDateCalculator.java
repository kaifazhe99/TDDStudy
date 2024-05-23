import java.time.LocalDate;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayDate payDate) {
        int addedMonths = payDate.getPayAmount() / 10_000;
        if (payDate.getFirstBillingDate() != null) {
            LocalDate candidateExp = payDate.getBillingDate().plusMonths(addedMonths);
            if (payDate.getFirstBillingDate().getDayOfMonth() != candidateExp.getDayOfMonth()) {
                return candidateExp.withDayOfMonth(payDate.getFirstBillingDate().getDayOfMonth());
            }
        }
        return payDate.getBillingDate().plusMonths(addedMonths);
    }
}
