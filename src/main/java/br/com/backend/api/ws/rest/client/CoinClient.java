package br.com.backend.api.ws.rest.client;

import br.com.backend.api.representation.CoinRepresentation;
import br.com.backend.business.model.Coin;
import br.com.backend.business.util.log.ConvertStackTrace;
import br.com.backend.business.util.log.SaveLog;
import java.time.Instant;
import java.util.Date;
import java.util.List;
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

    public Coin findByCode(String code) {

        try {

            Client client = ClientBuilder.newClient();
            Response response = client.target("http://economia.awesomeapi.com.br/json/list/" + code + "/1").
                    request(MediaType.APPLICATION_JSON).get(Response.class);

            List<CoinRepresentation> coinRepresentationList = response.readEntity(new GenericType<List<CoinRepresentation>>() {
            });

            if (coinRepresentationList != null) {

                CoinRepresentation cr = coinRepresentationList.get(0);
                return new Coin(cr.getCode(), null,
                        (!cr.getBid().isEmpty() ? Double.valueOf(cr.getBid()) : null),
                        (!cr.getAsk().isEmpty() ? Double.valueOf(cr.getAsk()) : null),
                        Date.from(Instant.now()));
            }

        } catch (NumberFormatException e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }

        return null;
    }
}
