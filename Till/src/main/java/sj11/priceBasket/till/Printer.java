package sj11.priceBasket.till;

import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Component;
import sj11.priceBasket.entities.DiscountApplied;
import sj11.priceBasket.entities.Product;
import sj11.priceBasket.entities.Ticket;
import sj11.priceBasket.exceptions.EmptyShoppingListException;

@Component
public class Printer {

    private final float epsilon = 0.001f;

    public void print(Ticket ticket) {
        if (isZero(ticket.getSubtotalInPounds())) {
            throw new EmptyShoppingListException("The basket is empty");
        }
        System.out.print("Subtotal: £");
        printPrice(ticket.getSubtotalInPounds());
        if (ticket.getDiscountsApplied().isEmpty()) {
            System.out.println("(No offers available)");
        } else {
            printDiscountsApplied(ticket.getDiscountsApplied());
        }
        System.out.print("Total price: £");
        printPrice(ticket.getTotalInPounds());
    }

    private void printDiscountsApplied(List<DiscountApplied> discountsList) {
        discountsList.forEach((discount) -> {
            printDiscountApplied(discount);
        });
    }

    private void printDiscountApplied(DiscountApplied discountApplied) {
        Product product = discountApplied.getProductId1();
        if (product != null && isNotZero(discountApplied.getProductRate1())) {
            System.out.println(product.getName() + " " + Math.round(discountApplied.getProductRate1()) + "% off: -" + Math.round(product.getPriceInPounds() * discountApplied.getProductRate1()) + "p");
        }
        product = discountApplied.getProductId2();
        if (product != null && isNotZero(discountApplied.getProductRate2())) {
            System.out.println(product.getName() + " " + Math.round(discountApplied.getProductRate2()) + "% off: -" + Math.round(product.getPriceInPounds() * discountApplied.getProductRate2()) + "p");
        }
        product = discountApplied.getProductId3();
        if (product != null && isNotZero(discountApplied.getProductRate3())) {
            System.out.println(product.getName() + " " + Math.round(discountApplied.getProductRate3()) + "% off: -" + Math.round(product.getPriceInPounds() * discountApplied.getProductRate3()) + "p");
        }
        product = discountApplied.getProductId4();
        if (product != null && isNotZero(discountApplied.getProductRate4())) {
            System.out.println(product.getName() + " " + Math.round(discountApplied.getProductRate4()) + "% off: -" + Math.round(product.getPriceInPounds() * discountApplied.getProductRate4()) + "p");
        }
        product = discountApplied.getProductId5();
        if (product != null && isNotZero(discountApplied.getProductRate5())) {
            System.out.println(product.getName() + " " + Math.round(discountApplied.getProductRate5()) + "% off: -" + Math.round(product.getPriceInPounds() * discountApplied.getProductRate5()) + "p");
        }
    }

    private boolean isNotZero(float factor1) {
        return !(Math.abs(factor1 - 0f) < this.epsilon);
    }

    private boolean isZero(float factor1) {
        return Math.abs(factor1 - 0f) < this.epsilon;
    }

    private void printPrice(float price) {
        System.out.format(Locale.UK, "%.2f%n", price);
    }
}
