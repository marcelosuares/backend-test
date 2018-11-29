package br.com.backend.api.representation;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
public class CoinRepresentation {

    private String code;
    private String name;
    private String bid;
    private String ask;

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

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    @Override
    public String toString() {
        return "CoinRepresentation{"
                + "code=" + code
                + ", name=" + name
                + ", bid=" + bid
                + ", ask=" + ask
                + '}';
    }
}
