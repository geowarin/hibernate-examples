package sj11.priceBasket.till;

import java.util.Arrays;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sj11.priceBasket.config.PersistenceAndBeansConfiguration;
import sj11.priceBasket.entities.DiscountApplied;
import sj11.priceBasket.entities.Product;
import sj11.priceBasket.entities.Ticket;
import sj11.priceBasket.exceptions.InvalidDiscountAppliedException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceAndBeansConfiguration.class})
public class DiscountApplierTest {

    @Autowired
    private DiscountApplier discountApplier;

    private final float epsilon = 0.001f;

    @Test
    public void applyDiscounts_applyDiscount1_totalIs10pLessThanSubtotal() throws InvalidDiscountAppliedException {
        Ticket ticket = new Ticket();
        Product p1 = new Product("Apples", 1f);
        DiscountApplied da1 = new DiscountApplied(p1, 10f, null, null, null, null, null, null, null, null);
        ticket.setDiscountsApplied(Arrays.asList(da1));
        ticket.setSubtotalInPounds(1f);

        discountApplier.applyDiscounts(ticket);

        assertTrue(compare(0.9f, ticket.getSubtotalInPounds() - 0.1f));
    }

    @Test
    public void applyDiscounts_applyDiscount1Twice_totalIs20pLessThanSubtotal() throws InvalidDiscountAppliedException {
        Ticket ticket = new Ticket();
        Product p1 = new Product("Apples", 1f);
        DiscountApplied da1 = new DiscountApplied(p1, 10f, null, null, null, null, null, null, null, null);
        DiscountApplied da2 = new DiscountApplied(p1, 10f, null, null, null, null, null, null, null, null);
        ticket.setDiscountsApplied(Arrays.asList(da1, da2));
        ticket.setSubtotalInPounds(2f);

        discountApplier.applyDiscounts(ticket);

        assertTrue(compare(1.8f, ticket.getSubtotalInPounds() - 0.2f));
    }

    @Test
    public void applyDiscounts_applyDiscount2_totalIs40pLessThanSubtotal() throws InvalidDiscountAppliedException {
        Ticket ticket = new Ticket();
        Product p1 = new Product("Soup", 0.65f);
        Product p2 = new Product("Soup", 0.65f);
        Product p3 = new Product("Bread", 0.8f);
        DiscountApplied da1 = new DiscountApplied(p1, 0f, p2, 0f, p3, 50f, null, null, null, null);
        ticket.setDiscountsApplied(Arrays.asList(da1));
        ticket.setSubtotalInPounds(2.1f);

        discountApplier.applyDiscounts(ticket);

        assertTrue(compare(1.7f, ticket.getSubtotalInPounds() - 0.4f));
    }

    @Test
    public void applyDiscounts_applyDiscount2DifferentOrder_totalIs40pLessThanSubtotalAnyway() throws InvalidDiscountAppliedException {
        Ticket ticket = new Ticket();
        Product p1 = new Product("Soup", 0.65f);
        Product p2 = new Product("Soup", 0.65f);
        Product p3 = new Product("Bread", 0.8f);
        DiscountApplied da1 = new DiscountApplied(p1, 0f, p3, 50f, p2, 0f, null, null, null, null);
        ticket.setDiscountsApplied(Arrays.asList(da1));
        ticket.setSubtotalInPounds(2.1f);

        discountApplier.applyDiscounts(ticket);

        assertTrue(compare(1.7f, ticket.getSubtotalInPounds() - 0.4f));
    }

    @Test
    public void applyDiscounts_noDiscountsApplied_totalIsSubtotal() throws InvalidDiscountAppliedException {
        Ticket ticket = new Ticket();
        ticket.setSubtotalInPounds(1f);

        discountApplier.applyDiscounts(ticket);

        assertTrue(compare(ticket.getTotalInPounds(), ticket.getSubtotalInPounds()));
        assertTrue(compare(1f, ticket.getTotalInPounds()));
    }

    private boolean compare(float factor1, float factor2) {
        return Math.abs(factor1 - factor2) < this.epsilon;
    }

}
