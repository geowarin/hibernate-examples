package sj11.priceBasket.exceptions;

public class EmptyShoppingListException extends RuntimeException {

    public EmptyShoppingListException(String message) {
        super(message);
    }
}
