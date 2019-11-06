package checkout;

import java.time.DateTimeException;
import java.time.LocalDate;

public abstract class Offer {
    LocalDate expirationDate;

    protected boolean checkExpirationDate() {
        return LocalDate.now().isBefore(expirationDate);
    }

    public abstract void apply(Check check);
}
