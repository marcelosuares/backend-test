package br.com.backend.business.dao;

import br.com.backend.business.model.CompanyAddress;
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
public class CompanyAddressDao {

    private static final Logger LOGGER = SaveLog.launchLog(CompanyAddressDao.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    /**
     *
     * @param companyAddress - entrar com o objeto CompanyAddress a ser inserido
     * @return - retorna o objeto CompanyAddress inserido
     */
    public CompanyAddress insert(CompanyAddress companyAddress) {

        try {

            entityManager.persist(companyAddress);
            return companyAddress;

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }

        return null;
    }

    /**
     * atualiza o endereço de empresa específica
     *
     * @param companyAddress - entrar com o objeto CompanyAddress a ser
     * atualizado
     */
    public void update(CompanyAddress companyAddress) {

        try {

            entityManager.merge(companyAddress);

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }
    }
}
