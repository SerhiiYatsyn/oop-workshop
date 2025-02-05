package checkout;

import java.util.ArrayList;
import java.util.List;

public class Check {
    private List<Product> products = new ArrayList<>();
    private int points = 0;

    public int getTotalCost() {
        int totalCost = 0;
        for (Product product : this.products) {
            totalCost += product.price;
        }
        return totalCost;
    }

    void addProduct(Product product) {
        products.add(product);
    }

    public int getTotalPoints() {
        return getTotalCost() + points;
    }

    void addPoints(int points) {
        this.points += points;
    }

    int getCostByCategory(Category category) {
        return products.stream()
                .filter(p -> p.category == category)
                .mapToInt(p -> p.price)
                .reduce(0, Integer::sum);
    }

    int getCostByTrademark(Trademark trademark) {
        return products.stream()
                .filter(p -> p.trademark == trademark)
                .mapToInt(p -> p.price)
                .reduce(0, Integer::sum);
    }

    int getCostByProduct(String name) {
        return products.stream()
                .filter(p -> p.name.equals(name))
                .mapToInt(p -> p.price)
                .reduce(0, Integer::sum);
    }

    int getHalfPriceOnProduct(String name) {
        return products.stream()
                .filter(p -> p.name.equals(name))
                .mapToInt(p -> p.price/2)
                .reduce(0, Integer::sum);
    }

}
