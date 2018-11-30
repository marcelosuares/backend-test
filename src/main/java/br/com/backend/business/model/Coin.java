package br.com.backend.business.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
@Entity
@Table(name = "tb_coin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coin.findAll", query = "SELECT c FROM Coin c"),
    @NamedQuery(name = "Coin.findByCode", query = "SELECT c FROM Coin c WHERE c.code = :code")
})
public class Coin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "coinIdGen", sequenceName = "sq_coin", allocationSize = 1)
    @GeneratedValue(generator = "coinIdGen", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tx_code")
    private String code;

    @Column(name = "tx_name")
    private String name;

    @Column(name = "rl_bid")
    private double bid;

    @Column(name = "rl_ask")
    private double ask;

    @Column(name = "dt_last_quote")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastQuote;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coin", fetch = FetchType.LAZY)
    private List<Company> companyList;

    public Coin() {
    }

    public Coin(String code, String name, double bid, double ask,
            Date lastQuote) {

        this.code = code;
        this.name = name;
        this.bid = bid;
        this.ask = ask;
        this.lastQuote = lastQuote;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public Date getLastQuote() {
        return lastQuote;
    }

    public void setLastQuote(Date lastQuote) {
        this.lastQuote = lastQuote;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.code);
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
        final Coin other = (Coin) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Coin{"
                + "id=" + id
                + ", code=" + code
                + ", name=" + name
                + ", bid=" + bid
                + ", ask=" + ask
                + ", lastQuote=" + lastQuote
                + ", companyList=" + companyList
                + '}';
    }
}
