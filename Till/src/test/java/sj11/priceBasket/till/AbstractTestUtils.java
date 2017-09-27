/*
 * THIS SOFTWARE IS PROVIDED BY COSTAIN LTD ``AS IS'', WITH NO WARRANTY, TERM
 * OR CONDITION OF ANY KIND, EXPRESS OR IMPLIED, AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 * EVENT SHALL COSTAIN LTD BE LIABLE FOR ANY LOSSES, CLAIMS OR DAMAGES OF
 * WHATEVER NATURE, INCLUDING ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGES OR LOSSES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE.
 *
 * Copyright 2017 © Costain Ltd.
 * All Rights Reserved.
 */

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
