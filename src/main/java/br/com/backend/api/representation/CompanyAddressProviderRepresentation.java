package br.com.backend.api.representation;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
public class CompanyAddressProviderRepresentation {

    private Integer id;
    private String street;
    private Integer number;
    private String neighborhood;
    private String complement;
    private String cep;
    private String city;

    public CompanyAddressProviderRepresentation() {
    }

    public CompanyAddressProviderRepresentation(Integer id,
            String street, Integer number, String neighborhood,
            String complement, String cep, String city) {

        this.id = id;
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.complement = complement;
        this.cep = cep;
        this.city = city;
    }

    //getters & setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
