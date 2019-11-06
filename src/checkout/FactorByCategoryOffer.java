package checkout;

public class FactorByCategoryOffer extends Offer {
    final Category category;
    final int factor;

    public FactorByCategoryOffer(Category category, int factor) {
        this.category = category;
        this.factor = factor;
    }

    public FactorByCategoryOffer(Category category, int factor, int shelfLifeYear, int shelfLifeMonth, int shelfLifeDay) {
        this.category = category;
        this.factor = factor;
        this.expirationDateYear = shelfLifeYear;
        this.expirationDateMonth = shelfLifeMonth;
        this.expirationDateDay = shelfLifeDay;
    }
    @Override
    public void apply(Check check) {
        int points = check.getCostByCategory(category);
        check.addPoints(points * (factor - 1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FactorByCategoryOffer that = (FactorByCategoryOffer) o;
        return factor == that.factor &&
                category == that.category;
    }

}
