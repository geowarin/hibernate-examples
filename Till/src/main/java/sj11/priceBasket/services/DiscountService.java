package sj11.priceBasket.services;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sj11.priceBasket.entities.Discount;
import sj11.priceBasket.repositories.DiscountRepository;

@Component
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    public Set<Discount> getAll() {
        return discountRepository.findAll();
    }
}
