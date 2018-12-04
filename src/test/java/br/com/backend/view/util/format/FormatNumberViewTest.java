package br.com.backend.view.util.format;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
public class FormatNumberViewTest {

    @Test
    public void doubleToMoneyBrStringTest() {

        String result = FormatNumberView.doubleToMoneyBrString(500.00);
        String expected = "R$ 500,00";
        Assert.assertEquals(expected, result);
    }
}
