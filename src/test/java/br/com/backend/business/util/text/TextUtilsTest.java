package br.com.backend.business.util.text;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
public class TextUtilsTest {

    @Test
    public void containsIgnoreCaracterSpecialTest() {

        boolean result = TextUtils.containsIgnoreCaracterSpecial("soares", "Marcelo Soares");
        boolean expected = true;
        Assert.assertEquals(expected, result);
    }

    @Test
    public void removeSpecialCaractersTest() {

        String result = TextUtils.removeSpecialCaracters("ãêó- ");
        String expected = "aeo";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void isValidEmailAddressTest() {

        boolean result = TextUtils.isValidEmailAddress("teste@uol.com");
        boolean expected = true;
        Assert.assertEquals(expected, result);
    }
}
