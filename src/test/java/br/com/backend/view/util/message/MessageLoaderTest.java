package br.com.backend.view.util.message;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
public class MessageLoaderTest {

    @Test
    public void getStringTest() {

        MessageLoader messageLoader = new MessageLoader();

        String result = messageLoader.getString("label.54e43tt8");
        String expected = "Sucesso";
        Assert.assertEquals(expected, result);
    }
}
