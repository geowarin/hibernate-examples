package sj11.priceBasket.till;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sj11.priceBasket.services.ProductService;
import sj11.priceBasket.entities.Product;
import sj11.priceBasket.entities.Ticket;
import sj11.priceBasket.exceptions.EmptyShoppingListException;

@Component
public class Scanner {

    @Autowired
    private ProductService productService;

    public Ticket scan(String[] shoppingItems) throws NoSuchElementException, EmptyShoppingListException {
        if (shoppingItems.length == 0) {
            throw new EmptyShoppingListException("The basket is empty");
        }
        Ticket ticket = new Ticket();
        for (String productName : shoppingItems) {
            scan(productName, ticket);
        }
        return ticket;
    }

    private void scan(String productName, Ticket ticket) throws NoSuchElementException {
        Product productFromDb = productService.findByName(productName);
        if (productFromDb != null) {
            ticket.getShoppingList().add(productFromDb);
            ticket.addToSubtotal(productFromDb.getPriceInPounds());
        } else {
            throw new NoSuchElementException("The product does not exist");
        }
    }
}
