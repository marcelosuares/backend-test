package br.com.backend.api.representation;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
public class CompanyProviderRepresentation {

    private Integer id;
    private String cnpj;
    private String name;
    private String email;
    private CompanyAddressProviderRepresentation capr;

    public CompanyProviderRepresentation() {
    }

    public CompanyProviderRepresentation(Integer id, String cnpj, String name,
            String email, CompanyAddressProviderRepresentation capr) {

        this.id = id;
        this.cnpj = cnpj;
        this.name = name;
        this.email = email;
        this.capr = capr;
    }

    //getters & setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CompanyAddressProviderRepresentation getCapr() {
        return capr;
    }

    public void setCapr(CompanyAddressProviderRepresentation capr) {
        this.capr = capr;
    }
}
