package checkout;

import java.time.DateTimeException;
import java.time.LocalDate;

public abstract class Offer {
    protected int expirationDateYear;
    protected int expirationDateMonth;
    protected int expirationDateDay;

    protected boolean checkSelfLife() {
        if(expirationDateYear == 0 && expirationDateMonth == 0 && expirationDateDay == 0) return true;
        LocalDate now,offerTime;
        try {
            now = LocalDate.now();
            offerTime = LocalDate.of(expirationDateYear, expirationDateMonth, expirationDateDay);
        } catch (DateTimeException e) {
            return false;
        }
        return now.isBefore(offerTime);
    }

    public abstract void apply(Check check);
}
