package br.com.backend.api.ws.rest.provider;

import br.com.backend.api.representation.CoinProviderRepresentation;
import br.com.backend.business.facade.CoinFacade;
import br.com.backend.business.model.Coin;
import br.com.backend.business.util.log.ConvertStackTrace;
import br.com.backend.business.util.log.SaveLog;
import br.com.backend.view.util.format.FormatDateTimeView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
@Path("coin")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class CoinProvider {

    private static final Logger LOGGER = SaveLog.launchLog(CoinProvider.class.getName());

    @EJB
    private CoinFacade coinFacade;

    /**
     *
     * @return - retorna todas as empresas
     */
    @GET
    @Path("all")
    public Response findAll() {

        try {

            List<Coin> coinList = coinFacade.findAll();
            List<CoinProviderRepresentation> coinProviderRepresentationList = new ArrayList<>();

            for (Coin c : coinList) {

                CoinProviderRepresentation cpr
                        = coinToCoinProviderRepresentation(c);

                coinProviderRepresentationList.add(cpr);
            }

            Gson gson = new GsonBuilder().create();
            return Response.ok(gson.toJson(coinProviderRepresentationList)).build();

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    /**
     *
     * @param code - entrar com o código da moeda (USD, EUR, ARS, GBP, BTC)
     * @return - retorna todas as empresas
     */
    @GET
    @Path("code/{code}")
    public Response findByCode(@NotNull @PathParam("code") String code) {

        try {
            Coin coin = findCoinByCode(code);

            if (coin == null) {

                return Response.status(Response.Status.BAD_REQUEST).build();

            } else {

                CoinProviderRepresentation cpr = coinToCoinProviderRepresentation(coin);

                Gson gson = new GsonBuilder().create();
                return Response.ok(gson.toJson(cpr)).build();
            }
        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    /**
     *
     * @param code - entrar com o código da moeda (USD, EUR, ARS, GBP, BTC)
     * @return - retorna o objeto Company referente
     */
    private Coin findCoinByCode(String code) {

        try {

            return coinFacade.findByCode(code);

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }

        return null;
    }

    /**
     * converte o objeto Coin em CoinProviderRepresentation
     *
     * @param coin - entrar com o objeto Coin
     * @return - retorna o objeto v
     */
    private CoinProviderRepresentation coinToCoinProviderRepresentation(Coin coin) {

        try {

            String dateFormated = FormatDateTimeView.toBrazilian(coin.getLastQuote());
            CoinProviderRepresentation cpr = new CoinProviderRepresentation(
                    coin.getId(),
                    coin.getCode(),
                    coin.getName(),
                    coin.getBid(),
                    coin.getAsk(),
                    dateFormated
            );

            return cpr;

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }

        return null;
    }
}
