package sj11.priceBasket.repositories;

import java.util.Set;
import org.springframework.data.repository.Repository;
import sj11.priceBasket.entities.Discount;

public interface DiscountRepository extends Repository<Discount, Long> {

    Set<Discount> findAll();
}
