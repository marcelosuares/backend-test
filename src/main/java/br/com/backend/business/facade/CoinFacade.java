package br.com.backend.business.facade;

import br.com.backend.business.dao.CoinDao;
import br.com.backend.business.model.Coin;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
@Stateless
public class CoinFacade {

    @Inject
    private CoinDao coinDao;

    /**
     * atualiza uma moeda específica
     *
     * @param coin - entrar com o objeto Coin a ser atualizado
     */
    public void update(Coin coin) {

        coinDao.update(coin);
    }

    /**
     *
     * @return - retorna todas as moedas de trabalho cadastradas
     */
    public List<Coin> findAll() {

        return coinDao.findAll();
    }

    /**
     *
     * @param code - entrar com o código da moeda (USD, EUR, ARS, GBP, BTC)
     * @return - retorna o objeto Coin referente
     */
    public Coin findByCode(String code) {

        return coinDao.findByCode(code);
    }
}
