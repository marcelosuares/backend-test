/*
 * Copyright (C) Unimed São Carlos - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Marcelo Soares <marcelo.suares@yahoo.com.br>, Jun 2016
 */
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

    /**
     *
     * @param company - entrar com o objeto Company a ser inserido
     * @return - retorna o objeto Company inserido
     */
    public Company insert(Company company) {

        return companyDao.insert(company);
    }

    /**
     * atualiza uma empresa específica
     *
     * @param company - entrar com o objeto Company a ser atualizado
     */
    public void update(Company company) {

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
}
