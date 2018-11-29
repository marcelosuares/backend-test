package br.com.backend.business.util.text;

import br.com.backend.business.util.log.ConvertStackTrace;
import br.com.backend.business.util.log.SaveLog;
import java.text.Normalizer;
import org.apache.log4j.Logger;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
public class TextUtils {

    private static final Logger LOGGER = SaveLog.launchLog(TextUtils.class.getName());

    /**
     *
     * @param textSearch - entrar com o texto a ser procurado
     * @param textTarget - entrar com o texto onde deva ser localizado
     * @return - retorna se o textTarget contem o textSearch ignorando os
     * caracteres especiais
     */
    public static boolean containsIgnoreCaracterSpecial(String textSearch, String textTarget) {

        try {

            if (textSearch != null && !textSearch.isEmpty()
                    && textTarget != null && !textTarget.isEmpty()) {
                textSearch = removeSpecialCaracters(textSearch);
                textTarget = removeSpecialCaracters(textTarget);

                if (textTarget.toLowerCase().contains(textSearch.toLowerCase())) {

                    return true;
                }
            }

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }

        return false;
    }

    /**
     *
     * @param text - entrar com o texto
     * @return - retorna o texto com os caracteres especiais removidos (ex:
     * acentos, cedilha)
     */
    public static String removeSpecialCaracters(String text) {

        try {

            String textProcessed = Normalizer.normalize(text, Normalizer.Form.NFD);
            textProcessed = textProcessed.replaceAll("[^\\p{ASCII}]", "");
            textProcessed = textProcessed.replaceAll("(\\(|\\)|\\-|\\/|\\.)", "");
            textProcessed = textProcessed.replaceAll(" ", "");
            return textProcessed;

        } catch (Exception e) {

            LOGGER.error(ConvertStackTrace.toString(e));
        }

        return null;
    }
}
