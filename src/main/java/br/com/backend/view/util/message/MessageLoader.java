package br.com.backend.view.util.message;

import br.com.backend.business.util.log.SaveLog;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
public class MessageLoader {

    private static final Logger LOGGER = SaveLog.launchLog(MessageLoader.class.getName());

    private Properties properties;
    private String messagesProperties = "/internationalization/bundle.properties";

    /**
     * carrega o arquivo bundle.properties
     */
    protected MessageLoader() {

        properties = new Properties();
        InputStream inputStream = MessageLoader.class.getResourceAsStream(messagesProperties);

        try {
            properties.load(inputStream);
            inputStream.close();

        } catch (IOException e) {

            LOGGER.error(e);
        }
    }

    /**
     *
     * @param key - entrar com a chave do bundle ex.: label.39jsur
     * @return - retorna o texto refernte a chave passada
     */
    public String getString(String key) {

        return (String) properties.getProperty(key);
    }
}
