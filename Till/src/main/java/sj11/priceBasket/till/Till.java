package sj11.priceBasket.till;

import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sj11.priceBasket.entities.Ticket;
import sj11.priceBasket.exceptions.EmptyShoppingListException;
import sj11.priceBasket.exceptions.InvalidDiscountAppliedException;

@Component
public class Till {

    @Autowired
    private Printer printer;
    @Autowired
    private Scanner scanner;
    @Autowired
    private DiscountApplier discountApplier;
    private final Logger logger = LoggerFactory.getLogger(Till.class);

    public void charge(String[] shoppingItems) {
        try {
            Ticket ticket = scanner.scan(shoppingItems);
            discountApplier.applyDiscounts(ticket);
            printer.print(ticket);
        } catch (NoSuchElementException notFoundEx) {
            System.out.println("One of the scanned products is not from this shop");
            logger.info(notFoundEx.getLocalizedMessage());
        } catch (InvalidDiscountAppliedException illegalStateEx) {
            System.out.println("Staff required");
            logger.info(illegalStateEx.getLocalizedMessage());
        } catch (EmptyShoppingListException emptyShoppingListEx) {
            System.out.println("Good bye!");
            logger.info(emptyShoppingListEx.getLocalizedMessage());
        }
    }
}
