package br.com.backend.view.converter;

import br.com.backend.business.facade.CoinFacade;
import br.com.backend.business.model.Coin;
import br.com.backend.business.util.log.SaveLog;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.apache.log4j.Logger;

/**
 * @author Marcelo Soares <marcelo@vztech.com.br>
 */
@FacesConverter(value = "coinConverter")
public class CoinConverter implements Converter {

    private static final Logger LOGGER = SaveLog.launchLog(CoinConverter.class.getName());

    @EJB
    private CoinFacade coinFacade;

    /*
     * Transforma o ID recebido na requisição em um objeto concreto
     * 
     * (non-Javadoc)
     * 
     * @see
     * javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext
     * , javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {

            if (value == null || value.equals("")) {
                return null;
            }

            Integer id = Integer.valueOf(value);

            List<Coin> coinList = coinFacade.findAll();

            for (Coin c : coinList) {
                if (c.getId().equals(id)) {
                    return c;
                }
            }

        } catch (NumberFormatException e) {

            LOGGER.error(e);
        }

        return null;
    }

    /*
     * Transforma um objeto concreto em sua representação como String para ser
     * enviado em uma requisição HTTP
     * 
     * (non-Javadoc)
     * 
     * @see
     * javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext
     * , javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object obj) {

        try {

            if (obj == null || obj.toString().equals("") || !(obj instanceof Coin)) {
                return "";
            }
            return ((Coin) obj).getId().toString();

        } catch (Exception e) {

            LOGGER.error(e);
        }

        return null;
    }
}
