package sj11.priceBasket.till;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sj11.priceBasket.config.PersistenceAndBeansConfiguration;
import sj11.priceBasket.entities.DiscountApplied;
import sj11.priceBasket.entities.Product;
import sj11.priceBasket.entities.Ticket;
import sj11.priceBasket.exceptions.EmptyShoppingListException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceAndBeansConfiguration.class})
public class PrinterTest extends AbstractTestUtils {

    @Autowired
    private Printer printer;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

    @Test
    public void print_example1FromDocument_ticketPrinted() {
        Ticket ticket = new Ticket();
        Product p1 = new Product("Apples", 1f);
        Product p2 = new Product("Milk", 1.3f);
        Product p3 = new Product("Bread", 0.8f);
        DiscountApplied discountApplied = new DiscountApplied(p1, 10f, null, null, null, null, null, null, null, null);
        List<Product> shoppingList = Arrays.asList(p1, p2, p3);
        ticket.setSubtotalInPounds(3.1f);
        ticket.setTotalInPounds(3f);
        ticket.setShoppingList(shoppingList);
        ticket.setDiscountsApplied(Arrays.asList(discountApplied));

        printer.print(ticket);

        assertEquals(createExpectation("£3.10", "£3.00", "Apples", "10%", "10p"), outContent.toString());
    }

    @Test
    public void print_example2FromDocument_ticketPrintedNoDiscountsApplied() {
        Ticket ticket = new Ticket();
        Product p2 = new Product("Milk", 1.3f);
        List<Product> shoppingList = Arrays.asList(p2);
        ticket.setSubtotalInPounds(1.3f);
        ticket.setTotalInPounds(1.3f);
        ticket.setShoppingList(shoppingList);

        printer.print(ticket);

        assertEquals(createExpectation("£1.30", "£1.30"), outContent.toString());
    }

    @Test(expected = EmptyShoppingListException.class)
    public void print_emptyTicket_throwsEmptyShoppingListException() {
        Ticket ticket = new Ticket();

        printer.print(ticket);
    }

}
