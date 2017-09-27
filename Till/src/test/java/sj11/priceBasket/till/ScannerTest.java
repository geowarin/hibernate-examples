package sj11.priceBasket.till;

import java.util.NoSuchElementException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sj11.priceBasket.config.PersistenceAndBeansConfiguration;
import sj11.priceBasket.entities.Ticket;
import sj11.priceBasket.exceptions.EmptyShoppingListException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceAndBeansConfiguration.class})
public class ScannerTest {

    @Autowired
    private Scanner scanner;

    @Test
    public void scan_calculateSubtotalOf1Product1_sameAsProductPrice() {
        String[] shoppingItems = {"Apples"};

        Ticket output = scanner.scan(shoppingItems);

        assertEquals(1f, output.getSubtotalInPounds(), 0.01f);
    }

    @Test
    public void scan_calculateSubtotalOf2SameProducts_2TimesThePrice() {
        String[] shoppingItems = {"Apples", "Apples"};

        Ticket output = scanner.scan(shoppingItems);

        assertEquals(2f, output.getSubtotalInPounds(), 0.01f);
    }

    @Test(expected = EmptyShoppingListException.class)
    public void scan_emptyShoppingList_throwsEmptyShoppingListException() {
        String[] shoppingItems = {};

        scanner.scan(shoppingItems);
    }

    @Test
    public void scan_calculateSubtotalOf2Products_sumOf2Products() {
        String[] shoppingItems = {"Apples", "Milk"};

        Ticket output = scanner.scan(shoppingItems);

        assertEquals(2.3f, output.getSubtotalInPounds(), 0.01f);
    }

    @Test
    public void scan_add2ProductsToShoppingList_shoppingListLength2() {
        String[] shoppingItems = {"Apples", "Milk"};

        Ticket output = scanner.scan(shoppingItems);

        assertEquals(2, output.getShoppingList().size());
    }

    @Test(expected = NoSuchElementException.class)
    public void scan_nonExistingProduct_throwsNoSuchElementException() {
        String[] shoppingItems = {"Milk", "NonExistingProduct"};

        scanner.scan(shoppingItems);
    }
}
