package checkout;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FactorByProductOffer extends Offer {
    private final int factor;
    private String productName;

    public FactorByProductOffer(int factor, String productName, LocalDate expirationDate) {
        this.factor = factor;
        this.productName = productName;
        this.expirationDate = expirationDate;
    }

    @Override
    protected boolean condition(Check check) {
        return true;
    }

    @Override
    protected int calculateBonus(Check check) {
        return check.getCostByProduct(productName) * (factor - 1);
    }

//    @Override
//    public void apply(Check check) {
//            int points = check.getCostByProduct(productName);
//            check.addPoints(points * (factor-1));
//        }
//    }
}
