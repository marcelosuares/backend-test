package br.com.backend.business.util.log;

import java.io.File;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
public class SaveLog {

    private final static String PATH = "/var/log/backend-test";

    /*
     * Esta é uma classe Helper, todos os seus metodos são estaticos,
     * assim, está classe não deve nunca ser instanciada por outro objeto.
     * Para resolver este problema adiciona-se um construtor privado para sobreescrever
     * o comportamento padrão do construtor implicito.
     */
    public static Logger launchLog(String nameClass) {

        try {

            checkDiretoryLog();

            Properties props = System.getProperties();
            //diretory logs
            props.setProperty("log", PATH);

            //Define the root logger with appender file
            props.setProperty("log4j.rootLogger", "INFO, FILE");

            //Define the file appender
            props.setProperty("log4j.appender.FILE", "org.apache.log4j.RollingFileAppender");
            props.setProperty("log4j.appender.FILE.File", "${log}/log.out");

            //Define the max size and max index file appender
            props.setProperty("log4j.appender.FILE.MaxFileSize", "20MB");
            props.setProperty("log4j.appender.FILE.MaxBackupIndex", "70");

            //Define the layout for file appender
            props.setProperty("log4j.appender.FILE.layout", "org.apache.log4j.PatternLayout");
            props.setProperty("log4j.appender.FILE.layout.conversionPattern", "%d{yyyy-MM-dd HH:mm:ss} %-5p | %C-%M - %m%n");

            PropertyConfigurator.configure(props);

            return Logger.getLogger(nameClass);

        } catch (Exception e) {

            e.printStackTrace(System.err);

        }

        return null;
    }

    private static void checkDiretoryLog() {

        try {

            File file = new File(PATH);

            if (!file.exists()) {

                file.mkdir();
            }

        } catch (Exception e) {

            e.printStackTrace(System.err);
        }
    }
}
