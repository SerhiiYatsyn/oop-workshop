package checkout;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FactorByTrademarkOffer extends Offer {
    private final int factor;
    private Trademark trademark;

    public FactorByTrademarkOffer(int factor, Trademark trademark, LocalDate expirationDate) {
        this.factor = factor;
        this.trademark = trademark;
        this.expirationDate = expirationDate;
    }

    @Override
    protected boolean condition(Check check) {
        return true;
    }

    @Override
    protected int calculateBonus(Check check) {
        return check.getCostByTrademark(trademark) * (factor - 1);
    }

//    @Override
//    public void apply(Check check) {
//            int points = check.getCostByTrademark(trademark);
//            check.addPoints(points * (factor-1));
//        }
//    }
}
