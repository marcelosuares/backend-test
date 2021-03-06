package br.com.backend.api.ws.rest.client;

import br.com.backend.api.representation.CompanyClientRepresentation;
import br.com.backend.business.model.Company;
import br.com.backend.business.model.CompanyAddress;
import br.com.backend.business.util.log.ConvertStackTrace;
import br.com.backend.business.util.log.SaveLog;
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

    /**
     *
     * @param cnpj - entrar com o cnpj da empresa
     * @return - retorna um objeto Company com dos dados da empresa
     */
    public Company findByCnpj(String cnpj) {

        try {

            if (cnpj != null) {

                //remove os caracteres especiais
                cnpj = cnpj.replaceAll("\\.", "");
                cnpj = cnpj.replaceAll("\\/", "");
                cnpj = cnpj.replaceAll("\\-", "");

                Client client = ClientBuilder.newClient();
                //obtem os dados da API
                Response response = client.target("http://www.receitaws.com.br/v1/cnpj/" + cnpj).
                        request(MediaType.APPLICATION_JSON).get(Response.class);

                CompanyClientRepresentation cr = response.readEntity(CompanyClientRepresentation.class);

                //verifica se contem dados no objeto
                if (cr != null && cr.getCnpj() != null) {

                    //retorna o objeto Company com dos dados da empresa
                    return new Company(cr.getCnpj(), cr.getNome(), cr.getEmail(),
                            new CompanyAddress(cr.getLogradouro(),
                                    (!cr.getNumero().isEmpty() ? Integer.valueOf(cr.getNumero()) : null), cr.getBairro(),
                                    cr.getComplemento(), cr.getCep(), cr.getMunicipio()));

                }
            }

        } catch (NumberFormatException e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }

        return null;
    }
}
