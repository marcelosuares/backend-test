package br.com.backend.business.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
@Entity
@Table(name = "tb_company")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Company.findAll",
            query = "SELECT c FROM Company c")
    ,
    @NamedQuery(name = "Company.findByCnpj",
            query = "SELECT c FROM Company c WHERE c.cnpj = :cnpj")
})
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "companyIdGen", sequenceName = "sq_company", allocationSize = 1)
    @GeneratedValue(generator = "companyIdGen", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tx_cnpj")
    private String cnpj;

    @Column(name = "tx_name")
    private String name;

    @Column(name = "tx_email")
    private String email;

    @JoinColumn(name = "id_coin", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Coin coin;

    @JoinColumn(name = "id_company_address", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CompanyAddress companyAddress;

    public Company() {
    }

    public Company(String cnpj, String name, String email,
            CompanyAddress companyAddress) {
        this.cnpj = cnpj;
        this.name = name;
        this.email = email;
        this.companyAddress = companyAddress;
    }

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

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public CompanyAddress getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(CompanyAddress companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.cnpj);
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
        final Company other = (Company) obj;
        if (!Objects.equals(this.cnpj, other.cnpj)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Company{"
                + "id=" + id
                + ", cnpj=" + cnpj
                + ", name=" + name
                + ", email=" + email
                + ", coin=" + coin
                + ", companyAddress=" + companyAddress
                + '}';
    }
}
