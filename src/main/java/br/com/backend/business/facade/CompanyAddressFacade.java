package br.com.backend.business.facade;

import br.com.backend.business.dao.CompanyAddressDao;
import br.com.backend.business.model.CompanyAddress;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
@Stateless
public class CompanyAddressFacade {

    @Inject
    private CompanyAddressDao companyAddressDao;

    /**
     *
     * @param companyAddress - entrar com o objeto CompanyAddress a ser inserido
     * @return - retorna o objeto CompanyAddress inserido
     */
    public CompanyAddress insert(CompanyAddress companyAddress) {

        return companyAddressDao.insert(companyAddress);
    }
}
