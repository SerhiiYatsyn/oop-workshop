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
    public void apply(Check check) {
        if (totalCost <= check.getTotalCost() && this.checkExpirationDate())
            check.addPoints(points);
    }
}
