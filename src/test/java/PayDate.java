
import java.time.LocalDate;


public class PayDate {
    private LocalDate billingDate;
    private int payAmount;

    private PayDate() {
    }

    public PayDate(LocalDate billingDate, int payAmount) {
        this.billingDate = billingDate;
        this.payAmount = payAmount;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PayDate data = new PayDate();

        public Builder billingDate(LocalDate billingDate) {
            data.billingDate = billingDate;
            return this;
        }

        public Builder payAmount(int payAmount) {
            data.payAmount = payAmount;
            return this;
        }

        public PayDate build() {
            return data;
        }


    }

}
