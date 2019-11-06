package checkout;

import java.time.DateTimeException;
import java.time.LocalDate;

public class FactorByCategoryOffer extends Offer {
    final Category category;
    final int factor;


    public FactorByCategoryOffer(Category category, int factor, LocalDate expirationDate) {
        this.category = category;
        this.factor = factor;
        this.expirationDate = expirationDate;
    }

    @Override
    public void apply(Check check) {
        if (this.checkExpirationDate()) {
            int points = check.getCostByCategory(category);
            check.addPoints(points * (factor - 1));
        }
    }
}
