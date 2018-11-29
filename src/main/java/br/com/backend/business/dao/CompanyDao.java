package br.com.backend.business.dao;

import br.com.backend.business.model.Company;
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
public class CompanyDao {

    private static final Logger LOGGER = SaveLog.launchLog(CompanyDao.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    /**
     *
     * @param company - entrar com o objeto Company a ser inserido
     * @return - retorna o objeto Company inserido
     */
    public Company insert(Company company) {

        try {

            entityManager.persist(company);
            return company;

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }

        return null;
    }

    /**
     * atualiza uma empresa específica
     *
     * @param company - entrar com o objeto Company a ser atualizado
     */
    public void update(Company company) {

        try {

            entityManager.merge(company);

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }
    }

    /**
     * remove uma empresa específica
     *
     * @param company - entrar com o objeto Company a ser removido
     */
    public void remove(Company company) {

        try {

            Company c = entityManager.merge(company);
            entityManager.remove(c);

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }
    }

    /**
     *
     * @return - retorna todas as empresas cadastradas
     */
    public List<Company> findAll() {

        try {

            TypedQuery<Company> typedQuery = entityManager
                    .createNamedQuery("Company.findAll", Company.class);
            typedQuery.setHint("javax.persistence.cache.storeMode", "REFRESH");

            return typedQuery.getResultList();

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }

        return null;
    }
}
