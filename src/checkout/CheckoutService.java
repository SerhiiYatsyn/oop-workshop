package checkout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutService {
    private Check check;
    private List<Offer> offers = new ArrayList<>();
    Map<String, Double> trademarks = new HashMap<>();

    public List<Offer> getOffers() {
        return offers;
    }


    public void openCheck() {
        check = new Check();
    }

    public void addProduct(Product product) {
        if (check == null) {
            openCheck();
        }
        check.addProduct(product);
    }

    public Check closeCheck() {
        Check closedCheck = check;
        getYourOffers();
        for (Offer offer : offers) {
            useOffer(offer);
        }

        check = null;
        return closedCheck;
    }

    private void useOffer(Offer offer) {
        if (check != null && offer.checkSelfLife())
            offer.apply(check);

    }

    public void deleteAllOffers() {
        this.offers.clear();
    }

    public void addOffer(Offer offer) {
        offers.add(offer);
    }

    public void deleteOffer(Offer offer) {
        offers.remove(offer);
    }

    public void getYourOffers() {
//        offers.add(new AnyGoodsOffer(6, 2,2050,10,10));
//        offers.add(new AnyGoodsOffer(7, 3,2050,10,10));
//        offers.add(new AnyGoodsOffer(8, 2,2050,10,10));
//        offers.add(new FactorByCategoryOffer(Category.MILK, 2,2050,10,10));
//        offers.add(new FactorByCategoryOffer(Category.MILK, 2,2050,10,10));
    }
}
