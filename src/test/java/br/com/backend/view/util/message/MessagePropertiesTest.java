package br.com.backend.view.util.message;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
public class MessagePropertiesTest {

    @Test
    public void getStringTest() {

        String result = MessageProperties.getString("message.843jhsdf");
        String expected = "Salvo com sucesso";
        Assert.assertEquals(expected, result);
    }
}
