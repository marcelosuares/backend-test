package br.com.backend.view.mb;

import br.com.backend.api.ws.rest.client.CoinClient;
import br.com.backend.api.ws.rest.client.CompanyClient;
import br.com.backend.business.facade.CoinFacade;
import br.com.backend.business.facade.CompanyFacade;
import br.com.backend.business.model.Coin;
import br.com.backend.business.model.Company;
import br.com.backend.business.model.CompanyAddress;
import br.com.backend.business.util.log.ConvertStackTrace;
import br.com.backend.business.util.log.SaveLog;
import br.com.backend.business.util.text.TextUtils;
import br.com.backend.view.util.message.MessageGrowl;
import br.com.backend.view.util.message.MessageProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
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
    private CompanyClient companyClient;

    @EJB
    private CoinClient coinClient;

    @EJB
    private CoinFacade coinFacade;

    private List<Company> companyList;
    private List<Company> companyListTmp;
    private Company company;
    private CompanyAddress companyAddress;
    private String searchText;
    private boolean showList;
    private List<Coin> coinList;
    private boolean showData;

    @PostConstruct
    protected void init() {

        loadData();
        updateCoin();
    }

    private void loadData() {

        companyList = companyFacade.findAll();
        companyListTmp = companyList;
        coinList = coinFacade.findAll();
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

        company = new Company();
        companyAddress = new CompanyAddress();
        showList = false;
        showData = false;
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

                Company c = companyFacade.findByCnpj(company.getCnpj());
                if (c != null) {

                    MessageGrowl.info(MessageProperties.getString("message.84kj458s"));
                    company = c;

                } else {

                    company = companyClient.findByCnpj(company.getCnpj());
                    companyAddress = company.getCompanyAddress();

                    showData = true;
                    RequestContext.getCurrentInstance().update("geral-form");
                }
            }

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }
    }

    public void saveNewCompany() {

        company.setCompanyAddress(companyAddress);

        if (company.getName() == null || company.getName().isEmpty()) {

            MessageGrowl.warn(MessageProperties.getString("message.0938jjh4"));

        } else if (company.getCompanyAddress().getStreet() == null || company.getCompanyAddress().getStreet().isEmpty()) {

            MessageGrowl.warn(MessageProperties.getString("message.03hfjdls"));

        } else if (company.getCompanyAddress().getCep() == null || company.getCompanyAddress().getCep().isEmpty()) {

            MessageGrowl.warn(MessageProperties.getString("message.37kjsdfh"));

        } else if (company.getCompanyAddress().getCity() == null || company.getCompanyAddress().getCity().isEmpty()) {

            MessageGrowl.warn(MessageProperties.getString("message.4ii345ii"));

        } else if (company.getCoin() == null) {

            MessageGrowl.warn(MessageProperties.getString("message.94jk45kk"));

        } else {

            companyFacade.insert(company);
            loadData();
            MessageGrowl.info(MessageProperties.getString("message.843jhsdf"));
            RequestContext.getCurrentInstance().update("geral-form");
        }
    }

    private void updateCoin() {

        int delayInSeconds = 60 * 5 * 1000;

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                for (Coin c : coinList) {

                    try {

                        Coin coin = coinClient.findByCode(c.getCode());
                        coin.setId(c.getId());
                        coin.setName(c.getName());
                        coinFacade.update(coin);

                    } catch (Exception e) {

                        LOGGER.error(ConvertStackTrace.toString(e));
                    }
                }
            }
        }, 0, delayInSeconds);
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

    public CompanyAddress getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(CompanyAddress companyAddress) {
        this.companyAddress = companyAddress;
    }

    public List<Coin> getCoinList() {
        return coinList;
    }

    public void setCoinList(List<Coin> coinList) {
        this.coinList = coinList;
    }

    public boolean isShowData() {
        return showData;
    }

    public void setShowData(boolean showData) {
        this.showData = showData;
    }
}
