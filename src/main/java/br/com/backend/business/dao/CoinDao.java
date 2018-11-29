package br.com.backend.business.dao;

import br.com.backend.business.model.Coin;
import br.com.backend.business.util.log.ConvertStackTrace;
import br.com.backend.business.util.log.SaveLog;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
@Stateless
public class CoinDao {

    private static final Logger LOGGER = SaveLog.launchLog(CoinDao.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * atualiza uma moeda específica
     *
     * @param coin - entrar com o objeto Coin a ser atualizado
     */
    public void update(Coin coin) {

        try {

            entityManager.merge(coin);

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }
    }
}
