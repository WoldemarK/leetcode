package org.example;
/*
Интернет магазин хочет повысить лояльность покупателей. Для этого начнет давать им персональные скидки.

Нужно сделать простую систему лояльности, которая дает % скидку на корзину.
Процент скидки зависит от пользователя.

Написать класс, который:
- на вход получает id пользователя и корзину
- вычисляет и применяет скидки
- возвращает корзину, в которой учтены скидки
- скидка учитывается в стоимости покупки

Корзина - список покупок пользователя.

Покупка:
- id товара
- цена
- итоговая стоимость c учетом скидки

Скидка. Для пользователя может быть задан % скидки (целое число).
*/

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class Shop {
    public Map<Users, Integer> discount;

    public Shop(Map<Users, Integer> discount) {
        this.discount = discount;
    }

    public Basket method(Users users, Basket basket) {
        Integer dis = discount.getOrDefault(users, 0);
        for (Product product : basket.getProducts()) {
            product.setTotal((100 - dis) * product.getTotal());
        }
        return basket;
    }

}
@ToString
@Getter
class Users {
    private Long id;
    public Users(Long id) {
        this.id = id;
    }
}
@ToString
@Getter
class Basket {
    private List<Product> products;
    public Basket(List<Product> products) {
        this.products = products;
    }
}
@Data
class Product {
    private Integer idProduct;
    private final Double price;
    private Double total;

    public Product(Integer idProduct, Double price, Double total) {
        this.idProduct = idProduct;
        this.price = price;
        this.total = total;
    }
}
class Mains{
    public static void main(String[] args) {
        Users users = new Users(1L);
        Product product1 = new Product(1,2.2,2.);
        Product product2 = new Product(2,3.,2.5);
        Basket basket = new Basket(List.of(product1, product2));
        Shop shop = new Shop(Map.of(users,1));
        System.out.println(shop);
        System.out.println(shop.method(users, basket));
    }}