package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Order order1 = new Order("123", Arrays.asList(
                new Product("Apple", 1),
                new Product("Orange", 2),
                new Product("Banana", 3)));

        Order order2 = new Order("456", Arrays.asList(
                new Product("Apple", 4),
                new Product("Grape", 5),
                new Product("Banana", 6)));

        List<Order> orders = Arrays.asList(order1, order2);

        List<Product> uniqueProducts = orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(uniqueProducts);
    }
}

class Order {

    private String orderId;
    private List<Product> products;

    public Order(String orderId, List<Product> products) {
        this.orderId = orderId;
        this.products = products;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<Product> getProducts() {
        return products;
    }
}


class Product {

    private String name;
    private int id;

    public Product(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}