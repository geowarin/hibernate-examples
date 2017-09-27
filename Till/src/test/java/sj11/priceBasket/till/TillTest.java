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

import java.io.PrintStream;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sj11.priceBasket.config.PersistenceAndBeansConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceAndBeansConfiguration.class})
public class TillTest extends AbstractTestUtils {

    @Autowired
    private Till till;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

    /**
     * Test of charge method, of class Till.
     */
    @Test
    public void charge_testCase1FromDocument_10poff() {
        String[] shoppingItems = {"Apples", "Milk", "Bread"};

        till.charge(shoppingItems);

        assertEquals(createExpectation("£3.10", "£3.00", "Apples", "10%", "10p"), outContent.toString());
    }

    @Test
    public void charge_testCase2FromDocument_noOfferApplied() {
        String[] shoppingItems = {"Milk"};

        till.charge(shoppingItems);

        assertEquals(createExpectation("£1.30", "£1.30"), outContent.toString());
    }

    @Test
    public void charge_apply2ndOffer_50percentOffInBread() {
        String[] shoppingItems = {"Soup", "Soup", "Bread"};

        till.charge(shoppingItems);

        assertEquals(createExpectation("£2.10", "£1.70", "Bread","50%", "40p"), outContent.toString());
    }

    @Test
    public void charge_apply2ndOffer_thereIsASoupLeftToApplyTheOfferAgain() {
        String[] shoppingItems = {"Soup", "Soup", "Bread", "Soup", "Bread"};

        till.charge(shoppingItems);

        assertEquals(createExpectation("£3.55", "£3.15", "Bread","50%", "40p"), outContent.toString());
    }

    @Test
    public void charge_apply2ndOfferDifferentOrder_50percentOffInBreadAnyway() {
        String[] shoppingItems = {"Soup", "Bread", "Soup"};

        till.charge(shoppingItems);

        assertEquals(createExpectation("£2.10", "£1.70", "Bread","50%", "40p"), outContent.toString());
    }

    @Test
    public void charge_emptyShoppingList_goodBye() {
        String[] shoppingItems = {};

        till.charge(shoppingItems);

        assertEquals("Good bye!\n", outContent.toString());
    }

    @Test
    public void charge_invalidProduct_informativeMessage() {
        String[] shoppingItems = {"invalidProduct"};

        till.charge(shoppingItems);

        assertEquals("One of the scanned products is not from this shop\n", outContent.toString());
    }



}
