package br.com.backend.view.util.format;

import br.com.backend.business.util.log.ConvertStackTrace;
import br.com.backend.business.util.log.SaveLog;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 * classe reponsavel por fazer a formatação de data e hora na view
 *
 * @author Marcelo S. Silva <marcelo.silva@unimedsaocarlos.com.br>
 */
@ManagedBean
@ViewScoped
public class FormatDateTimeView implements Serializable {

    private static final Logger LOGGER = SaveLog.launchLog(FormatDateTimeView.class.getName());

    /**
     *
     * @param date - entrar com a data a ser formatada
     * @return - retorna a data no formato brasileiro dd/MM/yyyy HH:mm:ss
     */
    public static String toBrazilian(Date date) {

        try {

            if (date != null) {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                return simpleDateFormat.format(date);
            }

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }

        return null;
    }

    /**
     *
     * @return - retorna a data atual formatada como dd/MM/yyyy HH:mm:ss
     */
    public static String dateTimeNowFormatBrazilian() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
