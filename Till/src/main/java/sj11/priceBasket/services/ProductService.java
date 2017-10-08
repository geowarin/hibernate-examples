package sj11.priceBasket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sj11.priceBasket.entities.Product;
import sj11.priceBasket.repositories.ProductRepository;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findByName(String productName) {
        return productRepository.findByName(productName);
    }
}
