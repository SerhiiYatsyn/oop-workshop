package checkout;

import java.time.LocalDate;

public class HalfPriceOffer extends Offer {
    private String productName;

    public HalfPriceOffer(String productName, LocalDate expirationDate) {
        this.productName = productName;
        this.expirationDate = expirationDate;
    }

    @Override
    protected boolean condition(Check check) {
        return true;
    }

    @Override
    protected int calculateBonus(Check check) {
        return -check.getHalfPriceOnProduct(productName);
    }

//    @Override
//    public void apply(Check check) {
//            int points = check.getCostByTrademark(trademark);
//            check.addPoints(points * (factor-1));
//        }
//    }
}
