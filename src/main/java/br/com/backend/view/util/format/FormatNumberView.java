package br.com.backend.view.util.format;

import br.com.backend.business.util.log.ConvertStackTrace;
import br.com.backend.business.util.log.SaveLog;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author Marcelo S. Silva <marcelo.silva@unimedsaocarlos.com.br>
 */
@ManagedBean
@ViewScoped
public class FormatNumberView implements Serializable {

    private static final Logger LOGGER = SaveLog.launchLog(FormatNumberView.class.getName());

    /**
     *
     * @param value - entrar com o valor em double
     * @return - retorna o valor formatado em R$ 0,00
     */
    public String doubleToMoneyBrString(double value) {

        try {

            return "R$ " + String.format("%.2f", value);

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }

        return null;
    }
}
