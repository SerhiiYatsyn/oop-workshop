package checkout;

import java.time.LocalDate;

public class AnyGoodsOffer extends Offer {
    public final int totalCost;
    public final int points;

    public AnyGoodsOffer(int totalCost, int points, LocalDate expirationDate) {
        this.totalCost = totalCost;
        this.points = points;
        this.expirationDate = expirationDate;
    }

    @Override
    protected boolean condition(Check check) {
        return totalCost <= check.getTotalCost();
    }

    @Override
    protected int calculateBonus(Check check) {
        return points;
    }

//    @Override
//    public void apply(Check check) {
//        if (totalCost <= check.getTotalCost())
//            check.addPoints(points);
//    }


}
