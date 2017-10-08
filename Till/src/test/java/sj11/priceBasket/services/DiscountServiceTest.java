package sj11.priceBasket.services;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sj11.priceBasket.config.PersistenceAndBeansConfiguration;
import sj11.priceBasket.repositories.DiscountRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceAndBeansConfiguration.class})
public class DiscountServiceTest {

    @Autowired
    private DiscountRepository discountRepository;

    @Test
    public void getAll_justCallIt_2Discounts() {
        assertEquals(2, discountRepository.findAll().size());
    }

}
