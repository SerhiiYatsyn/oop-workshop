package checkout;

import java.time.DateTimeException;
import java.time.LocalDate;

public abstract class Offer {
    LocalDate expirationDate;

    private boolean checkExpirationDate() {
        return LocalDate.now().isBefore(expirationDate);
    }

    protected abstract boolean condition(Check check);

    protected abstract int calculateBonus(Check check);

    public void apply(Check check) {
        if (checkExpirationDate() && condition(check)) {
            int points = calculateBonus(check);
            check.addPoints(points);
        }
    }

}
