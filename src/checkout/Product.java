package checkout;

public class Product {
    final int price;
    final String name;
    Category category;
    Trademark trademark;

    public Product(int price, String name, Category category, Trademark trademark) {
        this.price = price;
        this.name = name;
        this.category = category;
        this.trademark = trademark;
    }

    public Product(int price, String name, Trademark trademark) {
        this(price, name, null, trademark);
    }
}
