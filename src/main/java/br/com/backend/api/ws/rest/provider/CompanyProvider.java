package br.com.backend.api.ws.rest.provider;

import br.com.backend.api.representation.CompanyAddressProviderRepresentation;
import br.com.backend.api.representation.CompanyProviderRepresentation;
import br.com.backend.business.facade.CoinFacade;
import br.com.backend.business.facade.CompanyFacade;
import br.com.backend.business.model.Coin;
import br.com.backend.business.model.Company;
import br.com.backend.business.model.CompanyAddress;
import br.com.backend.business.util.log.ConvertStackTrace;
import br.com.backend.business.util.log.SaveLog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.swing.text.MaskFormatter;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.log4j.Logger;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
@Path("company")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class CompanyProvider {

    private static final Logger LOGGER = SaveLog.launchLog(CompanyProvider.class.getName());

    @EJB
    private CompanyFacade companyFacade;

    @EJB
    private CoinFacade coinFacade;

    /**
     *
     * @return - retorna todas as empresas
     */
    @GET
    @Path("all")
    public Response findAll() {

        try {

            List<Company> companyList = companyFacade.findAll();
            List<CompanyProviderRepresentation> companyProviderRepresentationList = new ArrayList<>();

            for (Company c : companyList) {

                CompanyProviderRepresentation cpr
                        = companyToCompanyProviderRepresentation(c);

                companyProviderRepresentationList.add(cpr);
            }

            Gson gson = new GsonBuilder().create();
            return Response.ok(gson.toJson(companyProviderRepresentationList)).build();

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    /**
     *
     * @param cnpj - entrar com o cnpj
     * @return - retorna todas as empresas
     */
    @GET
    @Path("cnpj/{cnpj}")
    public Response findByCnpj(@NotNull @PathParam("cnpj") String cnpj) {

        try {
            Company company = findCompanyByCnpj(cnpj);

            if (company == null) {

                return Response.status(Response.Status.BAD_REQUEST).build();

            } else {

                CompanyProviderRepresentation cpr = companyToCompanyProviderRepresentation(company);

                Gson gson = new GsonBuilder().create();
                return Response.ok(gson.toJson(cpr)).build();
            }
        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    /**
     *
     * @param cnpj - entrar com o cnpj
     * @return - retorna o status da requisição
     */
    @DELETE
    @Path("/remove/cnpj/{cnpj}")
    @Produces(MediaType.APPLICATION_XML)
    public Response remove(
            @NotNull @PathParam("cnpj") String cnpj) {

        try {

            Company company = findCompanyByCnpj(cnpj);
            companyFacade.remove(company);

            return Response.status(Response.Status.OK).build();

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    /**
     * 
     * @param cnpj - entrar com o cnpj da empresa
     * @param name - entrar com o nome da empresa
     * @param email - entrar com o e-mail da empresa
     * @param street - entrar com o logradouro da empresa
     * @param number - entrar com o numero do logradouro
     * @param neighborhood - entrar com o bairro do logradouro
     * @param complement - entrar com o complemento do logradouro
     * @param cep - entrar com o cep
     * @param city - entrar com a cidade
     * @param coinCode - entrar com o código da moeda (USD, EUR, ARS, GBP, BTC)
     * @return - retorna o status da requisição
     */
    @PUT
    @Path("/insert/cnpj/{cnpj}/name/{name}/email/{email}/street/{street}/number/{number}"
            + "/neighborhood/{neighborhood}/complement/{complement}/cep/{cep}/city/{city}/coinCode/{coinCode}")
    public Response insert(
            @NotNull @PathParam("cnpj") String cnpj,
            @NotNull @PathParam("name") String name,
            @PathParam("email") String email,
            @NotNull @PathParam("street") String street,
            @PathParam("number") String number,
            @PathParam("neighborhood") String neighborhood,
            @PathParam("complement") String complement,
            @NotNull @PathParam("cep") String cep,
            @NotNull @PathParam("city") String city,
            @NotNull @PathParam("coinCode") String coinCode
    ) {
        try {

            //verifica os campos obrigatórios
            if (cnpj.isEmpty() || name.isEmpty()
                    || street.isEmpty() || cep.isEmpty() || city.isEmpty() || coinCode.isEmpty()) {

                return Response.status(Response.Status.BAD_REQUEST).build();

            } else {

                Coin coin = coinFacade.findByCode(coinCode);
                CompanyAddress companyAddress = new CompanyAddress(street,
                        (!number.isEmpty() ? Integer.valueOf(number) : null),
                        neighborhood, complement, cep, city);

                Company company = new Company(cnpj, name, email, companyAddress, coin);
                companyFacade.insert(company);

                return Response.status(Response.Status.OK).build();
            }

        } catch (NumberFormatException e) {

            LOGGER.error(ConvertStackTrace.toString(e));
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    /**
     * converte o objeto Company em CompanyProviderRepresentation
     *
     * @param company - entrar com o objeto Company
     * @return - retorna o objeto CompanyProviderRepresentation
     */
    private CompanyProviderRepresentation companyToCompanyProviderRepresentation(Company company) {

        try {

            CompanyAddressProviderRepresentation capr
                    = new CompanyAddressProviderRepresentation(
                            company.getCompanyAddress().getId(),
                            company.getCompanyAddress().getStreet(),
                            company.getCompanyAddress().getNumber(),
                            company.getCompanyAddress().getNeighborhood(),
                            company.getCompanyAddress().getComplement(),
                            company.getCompanyAddress().getCep(),
                            company.getCompanyAddress().getCity());

            CompanyProviderRepresentation cpr
                    = new CompanyProviderRepresentation(
                            company.getId(),
                            company.getCnpj(),
                            company.getName(),
                            company.getEmail(),
                            capr);

            return cpr;

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }

        return null;
    }

    /**
     *
     * @param cnpj - entrar com o cnpj ex: 04025700000191 com 14 caracteres
     * @return - retorna o objeto Company referente
     */
    private Company findCompanyByCnpj(String cnpj) {

        try {

            //verifica se a variável cnpj contem 14 caracteres
            if (cnpj.length() == 14) {

                //formata a mascara ao cnpj
                MaskFormatter mask = new MaskFormatter("##.###.###/####-##");
                mask.setValueContainsLiteralCharacters(false);
                cnpj = mask.valueToString(cnpj);
            }

            return companyFacade.findByCnpj(cnpj);

        } catch (ParseException e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }

        return null;
    }
}
