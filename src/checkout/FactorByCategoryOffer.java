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
    protected boolean condition(Check check) {
        return true;
    }

    @Override
    protected int calculateBonus(Check check) {
        return check.getCostByCategory(category) * (factor - 1);
    }

//    @Override
//    public void apply(Check check) {
//            int points = check.getCostByCategory(category);
//            check.addPoints(points * (factor - 1));
//        }
//    }
}
