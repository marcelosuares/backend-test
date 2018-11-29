package br.com.backend.api.ws.rest.client;

import br.com.backend.api.representation.CoinRepresentation;
import br.com.backend.business.util.log.ConvertStackTrace;
import br.com.backend.business.util.log.SaveLog;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
@Stateless
public class CoinClient {

    private static final Logger LOGGER = SaveLog.launchLog(CoinClient.class.getName());

    public void test() {

        try {

            Client client = ClientBuilder.newClient();
            Response response = client.target("http://economia.awesomeapi.com.br/json/list/USD/1").
                    request(MediaType.APPLICATION_JSON).get(Response.class);
            
            List<CoinRepresentation> coinRepresentationList = response.readEntity(new GenericType<List<CoinRepresentation>>() {
            });
            
            if(coinRepresentationList != null) {
                
                CoinRepresentation coinRepresentation = coinRepresentationList.get(0);
                System.out.println(coinRepresentation);
            }

        } catch (RuntimeException e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CoinClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
