package sj11.priceBasket.till;

import java.io.ByteArrayOutputStream;

public abstract class AbstractTestUtils {

    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    protected String createExpectation(String subtotal, String total, String... rateOffAndDiscountedPairs) {
        StringBuilder expectation = new StringBuilder();
        expectation.append("Subtotal: ").append(subtotal);
        expectation.append(System.lineSeparator());
        if (rateOffAndDiscountedPairs.length == 0) {
            expectation.append("(No offers available)");
            expectation.append(System.lineSeparator());
        } else {
            int index = 0;
            for (String item : rateOffAndDiscountedPairs) {
                switch (index % 3) {
                    case 0:
                        expectation.append(item).append(" ");
                        break;
                    case 1:
                        expectation.append(item).append(" off: -");
                        break;
                    default:
                        expectation.append(item);
                        expectation.append(System.lineSeparator());
                        break;
                }
                index++;
            }
        }
        expectation.append("Total price: ").append(total);
        return expectation.append(System.lineSeparator()).toString();
    }
}
