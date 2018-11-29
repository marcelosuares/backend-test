package br.com.backend.api.ws.rest.client;

import br.com.backend.api.representation.CompanyRepresentation;
import br.com.backend.business.util.log.ConvertStackTrace;
import br.com.backend.business.util.log.SaveLog;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
@Stateless
public class CompanyClient {

    private static final Logger LOGGER = SaveLog.launchLog(CompanyClient.class.getName());

    public void test() {

        try {

            Client client = ClientBuilder.newClient();
            Response response = client.target("http://www.receitaws.com.br/v1/cnpj/27865757000102").
                    request(MediaType.APPLICATION_JSON).get(Response.class);
            
            CompanyRepresentation dto = response.readEntity( CompanyRepresentation.class);
            
            System.out.println("Output from Server .... \n");
            System.out.println(dto);

        } catch (RuntimeException e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CompanyClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
