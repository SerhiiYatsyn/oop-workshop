package checkout;

public class AnyGoodsOffer extends Offer {
    public final int totalCost;
    public final int points;

    public AnyGoodsOffer(int totalCost, int points) {
        this.totalCost = totalCost;
        this.points = points;
    }

    public AnyGoodsOffer(int totalCost, int points, int shelfLifeYear, int shelfLifeMonth, int shelfLifeDay) {
        this.totalCost = totalCost;
        this.points = points;
        this.expirationDateYear = shelfLifeYear;
        this.expirationDateMonth = shelfLifeMonth;
        this.expirationDateDay = shelfLifeDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnyGoodsOffer that = (AnyGoodsOffer) o;
        return totalCost == that.totalCost &&
                points == that.points;
    }

    @Override
    public void apply(Check check) {
        if (totalCost <= check.getTotalCost())
            check.addPoints(points);
    }
}
