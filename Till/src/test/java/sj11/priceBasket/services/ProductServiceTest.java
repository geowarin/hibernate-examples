package sj11.priceBasket.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sj11.priceBasket.config.PersistenceAndBeansConfiguration;
import sj11.priceBasket.repositories.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceAndBeansConfiguration.class})
public class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findByName_existingName_notNull() {
        assertNotNull(productRepository.findByName("Apples"));
    }

    @Test
    public void findByName_NonExistingName_null() {
        assertNull(productRepository.findByName("NonExistingProductName"));
    }

}
