package checkout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutService {
    private Check check;
    private List<Offer> offers = new ArrayList<>();

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
        for (Offer offer : offers) {
            useOffer(offer);
        }
        deleteAllOffers();
        check = null;
        return closedCheck;
    }

    private void useOffer(Offer offer) {
        if (check != null)
            offer.apply(check);

    }

    public void deleteAllOffers() {
        this.offers.clear();
    }

    public void addOffer(Offer offer) {
        offers.add(offer);
    }
}
