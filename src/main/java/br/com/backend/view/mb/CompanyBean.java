package br.com.backend.view.mb;

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
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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
    private CompanyClient companyClient;

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
    }

    /**
     * carrega os dados iniciais da página
     */
    public void loadData() {

        companyList = companyFacade.findAll();
        companyListTmp = companyList;
        coinList = coinFacade.findAll();
        showList = true;
        search();
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

                    //procura pelo cnpj, nome e e-mail da empresa
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

    /**
     * prepara as variáveis para cadastrar uma nova empresa
     */
    public void prepareNewCompany() {

        company = new Company();
        companyAddress = new CompanyAddress();
        showList = false;
        showData = false;
        RequestContext.getCurrentInstance().update("geral-form");
    }

    /**
     * cancela o cadastro de uma nova empresa
     */
    public void cancelNewCompany() {

        company = null;
        loadData();
        RequestContext.getCurrentInstance().update("geral-form");
    }

    /**
     * prepra as variáveis para a edição de uma empresa
     *
     * @param company - entrar com o objeto Company a se editado
     */
    public void prepareEditCompany(Company company) {

        this.company = company;
        this.companyAddress = company.getCompanyAddress();
        showList = false;
        showData = true;
        RequestContext.getCurrentInstance().update("geral-form");
    }

    /**
     * remove uma empresa
     */
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

    /**
     * checa se o cnpj já está cadastrado ou obtem os dados da api para um novo
     * cadastro
     */
    public void checkCnpj() {

        try {

            //cehca se o cnpj não está nulo ou vazio
            if (company.getCnpj() == null || company.getCnpj().isEmpty()) {

                MessageGrowl.warn(MessageProperties.getString("message.49fgjh43"));

            } else {

                String cnpj = company.getCnpj();
                Company c = companyFacade.findByCnpj(cnpj);

                //se o objeto Company segnifica que já está cadastrado no banco de dados
                if (c != null) {

                    //mensagem que está cadastrado
                    MessageGrowl.warn(MessageProperties.getString("message.84kj458s"));

                } else {

                    //procura a empresa na api
                    company = companyClient.findByCnpj(cnpj);
                    if (company != null) {

                        //seta o endereço da empresa
                        companyAddress = company.getCompanyAddress();

                    } else {

                        //não foi encontado no base de dados nem na api
                        company = new Company();
                        company.setCnpj(cnpj);
                    }

                    showData = true;
                    RequestContext.getCurrentInstance().update("geral-form");
                }
            }

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }
    }

    /**
     * salva a companhia podendo ser uma inserção ou atualização
     */
    public void saveCompany() {

        //seta o endereço da empresa no objeto Company
        company.setCompanyAddress(companyAddress);

        //validação dos campos
        if (company.getName() == null || company.getName().isEmpty()) {

            MessageGrowl.warn(MessageProperties.getString("message.0938jjh4"));

        } else if (company.getEmail() != null && !TextUtils.isValidEmailAddress(company.getEmail())) {

            MessageGrowl.warn(MessageProperties.getString("message.649kfdh5"));

        } else if (company.getCompanyAddress().getStreet() == null || company.getCompanyAddress().getStreet().isEmpty()) {

            MessageGrowl.warn(MessageProperties.getString("message.03hfjdls"));

        } else if (company.getCompanyAddress().getCep() == null || company.getCompanyAddress().getCep().isEmpty()) {

            MessageGrowl.warn(MessageProperties.getString("message.37kjsdfh"));

        } else if (company.getCompanyAddress().getCity() == null || company.getCompanyAddress().getCity().isEmpty()) {

            MessageGrowl.warn(MessageProperties.getString("message.4ii345ii"));

        } else if (company.getCoin() == null) {

            MessageGrowl.warn(MessageProperties.getString("message.94jk45kk"));

        } else {

            //caso o id seja num é uma nova empresa
            if (company.getId() == null) {

                //inseri uma nova empresa
                companyFacade.insert(company);

            } else {

                //atualiza uma nova empresa
                companyFacade.update(company);
            }
            loadData();
            MessageGrowl.info(MessageProperties.getString("message.843jhsdf"));
            RequestContext.getCurrentInstance().update("geral-form");
        }
    }

    /**
     *
     * @param date - entrar com a data
     * @return - retorna se data passada com relação a atual está a mais de 20
     * minutos
     */
    public boolean lastQuoteUpOver20Minutes(Date date) {

        try {

            if (date != null) {

                //diferença em milisegundos
                long diff = Date.from(Instant.now()).getTime() - date.getTime();
                long diffInMinutes = (diff / 1000) / 60;

                if (diffInMinutes > 20) {

                    return true;
                }
            }

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }

        return false;
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
