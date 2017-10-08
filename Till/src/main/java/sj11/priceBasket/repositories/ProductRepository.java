package sj11.priceBasket.repositories;

import org.springframework.data.repository.Repository;
import sj11.priceBasket.entities.Product;

public interface ProductRepository extends Repository<Product, Long> {

    Product findByName(String name);
}
