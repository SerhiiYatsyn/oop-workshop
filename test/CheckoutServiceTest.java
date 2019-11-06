import checkout.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CheckoutServiceTest {

    private Product milk_7;
    private CheckoutService checkoutService;
    private Product bred_3;

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();
        checkoutService.openCheck();

        milk_7 = new Product(7, "Milk", Category.MILK);
        bred_3 = new Product(3, "Bred");
    }

    @Test
    void closeCheck__withOneProduct() {
        checkoutService.addProduct(milk_7);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(7));
    }

    @Test
    void closeCheck__withTwoProducts() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(10));
    }

    @Test
    void addProduct__whenCheckIsClosed__opensNewCheck() {
        checkoutService.addProduct(milk_7);
        Check milkCheck = checkoutService.closeCheck();
        assertThat(milkCheck.getTotalCost(), is(7));

        checkoutService.addProduct(bred_3);
        Check bredCheck = checkoutService.closeCheck();
        assertThat(bredCheck.getTotalCost(), is(3));
    }

    @Test
    void closeCheck__calcTotalPoints() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(10));
    }

    @Test
    void useOffer__addOfferPoints() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.addOffer(new AnyGoodsOffer(6, 2));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(12));
    }

    @Test
    void useOffer__whenCostLessThanRequired__doNothing() {
        checkoutService.addProduct(bred_3);

        checkoutService.addOffer(new AnyGoodsOffer(6, 2));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(3));
    }

    @Test
    void useOffer__factorByCategory() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.addOffer(new FactorByCategoryOffer(Category.MILK, 2));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer__closeCheckout() {
        Check check = checkoutService.closeCheck();
        milk_7 = new Product(7, "Milk", Category.MILK);
        milk_7 = new Product(7, "Milk", Category.MILK);
        milk_7 = new Product(7, "Milk", Category.MILK);
        bred_3 = new Product(3, "Bred");
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        assertThat(check.getTotalPoints(), is(0));
    }

    @Test
    void useOffer__checkShelfLife() {
        checkoutService = new CheckoutService();
        checkoutService.openCheck();
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        checkoutService.addOffer(new FactorByCategoryOffer(Category.MILK, 2));

        checkoutService.addOffer(new AnyGoodsOffer(6, 2, 2050, 10, 10));
        checkoutService.addOffer(new AnyGoodsOffer(6, 2, 2012, 10, 10));

        checkoutService.addOffer(new AnyGoodsOffer(6, 2, 2012, -1, 10));

        checkoutService.addOffer(new FactorByCategoryOffer(Category.MILK, 2, 2050, 10, 10));
        checkoutService.addOffer(new FactorByCategoryOffer(Category.MILK, 2, 2015, 10, 10));

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(26));

    }

    @Test
    void add__delete__offers() {
        checkoutService = new CheckoutService();
        checkoutService.addOffer(new FactorByCategoryOffer(Category.MILK, 2,2035,1,1));
        checkoutService.addOffer(new FactorByCategoryOffer(Category.MILK, 2,2036,2,5));
        checkoutService.addOffer(new FactorByCategoryOffer(Category.MILK, 2,2037,3,9));
        checkoutService.addOffer(new FactorByCategoryOffer(Category.MILK, 2,2040,10,12));

        Assertions.assertEquals(checkoutService.getOffers().size(), 4);

        checkoutService.deleteOffer(new FactorByCategoryOffer(Category.MILK, 2,2035,1,1));

        Assertions.assertEquals(checkoutService.getOffers().size(), 3);
    }

    }
