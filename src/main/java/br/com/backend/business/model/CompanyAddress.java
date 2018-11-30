package br.com.backend.business.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
@Entity
@Table(name = "tb_company_address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompanyAddress.findAll", query = "SELECT c FROM CompanyAddress c")
})
public class CompanyAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "companyAddressIdGen", sequenceName = "sq_company_address", allocationSize = 1)
    @GeneratedValue(generator = "companyAddressIdGen", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tx_street")
    private String street;

    @Column(name = "it_number")
    private Integer number;

    @Column(name = "tx_neighborhood")
    private String neighborhood;

    @Column(name = "tx_complement")
    private String complement;

    @Column(name = "tx_cep")
    private String cep;

    @Column(name = "tx_city")
    private String city;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyAddress", fetch = FetchType.LAZY)
    private List<Company> companyList;

    public CompanyAddress() {
    }

    public CompanyAddress(String street, Integer number, String neighborhood,
            String complement, String cep, String city) {
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.complement = complement;
        this.cep = cep;
        this.city = city;
    }

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

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CompanyAddress other = (CompanyAddress) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CompanyAddress{"
                + "id=" + id
                + ", street=" + street
                + ", number=" + number
                + ", neighborhood=" + neighborhood
                + ", complement=" + complement
                + ", cep=" + cep
                + ", city=" + city
                + ", companyList=" + companyList
                + '}';
    }
}
