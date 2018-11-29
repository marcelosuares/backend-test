package br.com.backend.view.mb;

import br.com.backend.api.client.CoinApiClient;
import br.com.backend.api.client.CompanyApiClient;
import br.com.backend.business.facade.CompanyFacade;
import br.com.backend.business.model.Company;
import br.com.backend.business.util.log.ConvertStackTrace;
import br.com.backend.business.util.log.SaveLog;
import br.com.backend.business.util.text.TextUtils;
import br.com.backend.view.util.message.MessageGrowl;
import br.com.backend.view.util.message.MessageProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
@ManagedBean
@ViewScoped
public class CompanyBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = SaveLog.launchLog(CompanyBean.class.getName());

    @EJB
    private CompanyFacade companyFacade;

    @EJB
    private CompanyApiClient companyApiClient;

    @EJB
    private CoinApiClient coinApiClient;

    private List<Company> companyList;
    private List<Company> companyListTmp;
    private Company company;
    private String searchText;
    private boolean showList;

    @PostConstruct
    protected void init() {

        loadData();
    }

    private void loadData() {

        companyList = companyFacade.findAll();
        companyListTmp = companyList;
        showList = true;
    }

    /**
     * pesquisa a lista da página
     */
    public void search() {

        try {

            companyList = companyListTmp;

            if (searchText != null) {

                List<Company> list = new ArrayList<>();

                for (Company c : companyList) {

                    if ((TextUtils.containsIgnoreCaracterSpecial(searchText, c.getCnpj())
                            || TextUtils.containsIgnoreCaracterSpecial(searchText, c.getName())
                            || TextUtils.containsIgnoreCaracterSpecial(searchText, c.getEmail()))
                            && !list.contains(c)) {

                        list.add(c);
                    }
                }

                companyList = list;
            }

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }
    }

    public void prepareNewCompany() {

        //companyApiClient.test();
        coinApiClient.test();

        company = new Company();
        showList = false;
        RequestContext.getCurrentInstance().update("geral-form");
    }

    public void cancelNewCompany() {

        company = null;
        loadData();
        RequestContext.getCurrentInstance().update("geral-form");
    }

    public void removeCompany() {

        try {

            if (company != null) {

                companyFacade.remove(company);
                loadData();
                MessageGrowl.info(MessageProperties.getString("message.4wg0yo7u"));
                RequestContext.getCurrentInstance().execute("PF('remove-dialog').hide()");
                RequestContext.getCurrentInstance().update("geral-form");

            }

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }
    }

    public void checkCnpj() {

        try {

            if (company.getCnpj() == null || company.getCnpj().isEmpty()) {

                MessageGrowl.warn(MessageProperties.getString("message.49fgjh43"));

            } else {

            }

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }
    }

    //getters && setters
    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public boolean isShowList() {
        return showList;
    }

    public void setShowList(boolean showList) {
        this.showList = showList;
    }
}
