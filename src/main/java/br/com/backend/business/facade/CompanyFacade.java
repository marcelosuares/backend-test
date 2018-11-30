package br.com.backend.business.facade;

import br.com.backend.business.dao.CompanyDao;
import br.com.backend.business.model.Company;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
@Stateless
public class CompanyFacade {

    @Inject
    private CompanyDao companyDao;

    @Inject
    private CompanyAddressFacade companyAddressFacade;

    /**
     *
     * @param company - entrar com o objeto Company a ser inserido
     * @return - retorna o objeto Company inserido
     */
    public Company insert(Company company) {

        //inseri o objeto CompanyAddress - endereço da empresa antes de inserir a mesma
        companyAddressFacade.insert(company.getCompanyAddress());

        return companyDao.insert(company);
    }

    /**
     * atualiza uma empresa específica
     *
     * @param company - entrar com o objeto Company a ser atualizado
     */
    public void update(Company company) {

        //inseri o objeto CompanyAddress - endereço da empresa antes de inserir a mesma
        companyAddressFacade.update(company.getCompanyAddress());
        
        companyDao.update(company);
    }

    /**
     * remove uma empresa específica
     *
     * @param company - entrar com o objeto Company a ser removido
     */
    public void remove(Company company) {

        companyDao.remove(company);
    }

    /**
     *
     * @return - retorna todas as empresas cadastradas
     */
    public List<Company> findAll() {

        return companyDao.findAll();
    }

    /**
     *
     * @param cnpj - entrar com o cnpj
     * @return - retorna o objeto Company referente
     */
    public Company findByCnpj(String cnpj) {

        return companyDao.findByCnpj(cnpj);
    }
}
