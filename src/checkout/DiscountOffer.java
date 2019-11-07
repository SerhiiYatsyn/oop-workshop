package checkout;

public class DiscountOffer extends Offer implements Reward {

    @Override
    protected boolean condition(Check check) {
        return false;
    }

    @Override
    protected int calculateBonus(Check check) {
        return 0;
    }
}
