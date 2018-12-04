package br.com.backend.view.util.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
public class FormatDateTimeViewTest {
    
    @Test
    public void toBrazilianTest() throws ParseException {
        
        Date date = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).parse( "2018-12-03 22:04:00");
        String result = FormatDateTimeView.toBrazilian(date);
        String expected = "03/12/2018 22:04:00";
        Assert.assertEquals(expected, result);
    }
}

