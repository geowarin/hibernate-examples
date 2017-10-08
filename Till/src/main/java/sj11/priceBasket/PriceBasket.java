package sj11.priceBasket;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sj11.priceBasket.till.Till;

public class PriceBasket {

    private static final String CONFIG_PACKAGE = "sj11.priceBasket.config";

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CONFIG_PACKAGE);
        context.getBean(Till.class).charge(args);
    }
}
