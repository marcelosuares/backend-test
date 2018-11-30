package br.com.backend.business.task;

import br.com.backend.api.ws.rest.client.CoinClient;
import br.com.backend.business.facade.CoinFacade;
import br.com.backend.business.model.Coin;
import br.com.backend.business.util.log.SaveLog;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.apache.log4j.Logger;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
@Singleton
@Startup
public class StartupTask {

    private static final Logger LOGGER = SaveLog.launchLog(StartupTask.class.getName());

    @EJB
    private CoinClient coinClient;

    @EJB
    private CoinFacade coinFacade;

    @PostConstruct
    protected void init() {

        updateCoinQuotation();
    }

    /**
     * atualiza a cotação das moedas de trabalho
     */
    private void updateCoinQuotation() {

        List<Coin> coinList = coinFacade.findAll();

        //aguarda 5 minutos para atualizar
        int delayInSeconds = 60 * 5;
        int delayInMilliseconds = delayInSeconds * 1000;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                for (Coin c : coinList) {

                    try {

                        //obtem a cotação atual da moeda via api
                        Coin coin = coinClient.findByCode(c.getCode());
                        coin.setId(c.getId());
                        coin.setName(c.getName());
                        //atualiza localmente
                        coinFacade.update(coin);

                    } catch (Exception e) {
                    }
                }
            }
        }, 0, delayInMilliseconds);
    }
}
