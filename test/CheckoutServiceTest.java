import checkout.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

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

        milk_7 = new Product(7, "Milk", Category.MILK, Trademark.Milk_and_the_City);
        bred_3 = new Product(3, "Bred", Trademark.Organic_milk);
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

        checkoutService.addOffer(new AnyGoodsOffer(6, 2, LocalDate.now().plusDays(1)));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(12));
    }

    @Test
    void useOffer__whenCostLessThanRequired__doNothing() {
        checkoutService.addProduct(bred_3);

        checkoutService.addOffer(new AnyGoodsOffer(6, 2, LocalDate.now().plusDays(1)));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(3));
    }

    @Test
    void useOffer__factorByCategory() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        checkoutService.addOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.now().plusDays(1)));
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(31));
    }

    @Test
    void useOffer__closeCheckout() {
        Check check = checkoutService.closeCheck();
        milk_7 = new Product(7, "Milk", Category.MILK, Trademark.Milk_and_the_City);
        milk_7 = new Product(7, "Milk", Category.MILK, Trademark.Organic_milk);
        milk_7 = new Product(7, "Milk", Category.MILK, Trademark.Organic_milk);
        bred_3 = new Product(3, "Bred", Trademark.Organic_milk);
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

        checkoutService.addOffer(new AnyGoodsOffer(6, 2, LocalDate.now().plusDays(1))); //12
        checkoutService.addOffer(new AnyGoodsOffer(6, 2, LocalDate.of(2012, 10, 10)));
        checkoutService.addOffer(new AnyGoodsOffer(6, 2, LocalDate.of(2012, 5, 10)));

        checkoutService.addOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.now().plusDays(1)));
        checkoutService.addOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.of(2015, 10, 10)));

        Check check = checkoutService.closeCheck();
        assertThat(check.getTotalPoints(), is(19));

    }

    @Test
    void add__delete__offers() {
        checkoutService = new CheckoutService();
        checkoutService.addOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.now().plusDays(1)));
        checkoutService.addOffer(new FactorByCategoryOffer(Category.MILK, 2,LocalDate.now().plusDays(1)));
        checkoutService.addOffer(new FactorByCategoryOffer(Category.MILK, 2,LocalDate.now().plusDays(1)));
        checkoutService.addOffer(new FactorByCategoryOffer(Category.MILK, 2, LocalDate.now().plusDays(1)));

        Assertions.assertEquals(checkoutService.getOffers().size(), 4);
        checkoutService.deleteAllOffers();
        Assertions.assertEquals(checkoutService.getOffers().size(), 0);

    }

    @Nested
    @DisplayName("FactorByTrademarkTest")
    class FactorByTrademarkTest {
        @Test
        void checkFactorByTrademark() {
            checkoutService = new CheckoutService();
            checkoutService.addProduct(new Product(6, "milk", Category.MILK, Trademark.Organic_milk));
            checkoutService.addOffer(new FactorByTrademarkOffer(2,Trademark.Organic_milk,LocalDate.now().plusDays(1)));

            Check check = checkoutService.closeCheck();
            assertThat(check.getTotalPoints(), is(12));
        }
    }

    @Nested
    @DisplayName("FactorByProductTest")
    class FactorByProductTest {
        @Test
        void checkFactorByTrademark() {
            checkoutService = new CheckoutService();
            checkoutService.addProduct(new Product(6, "milk", Category.MILK, Trademark.Organic_milk));
            checkoutService.addProduct(new Product(6, "someElse", Category.MILK, Trademark.Organic_milk));
            checkoutService.addOffer(new FactorByProductOffer(3,"milk",LocalDate.now().plusDays(1)));

            Check check = checkoutService.closeCheck();
            assertThat(check.getTotalPoints(), is(24));
        }
    }
}