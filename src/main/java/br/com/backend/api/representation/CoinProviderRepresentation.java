package br.com.backend.api.representation;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
public class CoinProviderRepresentation {

    private Integer id;
    private String code;
    private String name;
    private double bid;
    private double ask;
    private String lastQuote;

    public CoinProviderRepresentation() {
    }

    public CoinProviderRepresentation(Integer id, String code, String name, double bid, double ask, String lastQuote) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.bid = bid;
        this.ask = ask;
        this.lastQuote = lastQuote;
    }

    //getters & setters
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

    public String getLastQuote() {
        return lastQuote;
    }

    public void setLastQuote(String lastQuote) {
        this.lastQuote = lastQuote;
    }
}
