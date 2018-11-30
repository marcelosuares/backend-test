package br.com.backend.business.dao;

import br.com.backend.business.model.Coin;
import br.com.backend.business.util.log.ConvertStackTrace;
import br.com.backend.business.util.log.SaveLog;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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

    /**
     *
     * @return - retorna todas as moedas de trabalho cadastradas
     */
    public List<Coin> findAll() {

        try {

            TypedQuery<Coin> typedQuery = entityManager
                    .createNamedQuery("Coin.findAll", Coin.class);
            typedQuery.setHint("javax.persistence.cache.storeMode", "REFRESH");

            return typedQuery.getResultList();

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }

        return null;
    }
    
     /**
     *
     * @param code - entrar com o código da moeda (USD, EUR, ARS, GBP, BTC)
     * @return - retorna o objeto Coin referente
     */
    public Coin findByCode(String code) {

        try {

            TypedQuery<Coin> typedQuery = entityManager
                    .createNamedQuery("Coin.findByCode", Coin.class);
            typedQuery.setParameter("code", code);
            typedQuery.setHint("javax.persistence.cache.storeMode", "REFRESH");

            List<Coin> coinList = typedQuery.getResultList();
            if (coinList.size() == 1) {

                return coinList.get(0);
            }

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }

        return null;
    }
}
