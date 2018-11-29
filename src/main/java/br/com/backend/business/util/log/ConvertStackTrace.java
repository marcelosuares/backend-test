package br.com.backend.business.util.log;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
public class ConvertStackTrace {

    /**
     * Converte o tipo de erro Throwable para uma string
     *
     * @param e - entrar com a excessão do tipo Throwable
     * @return - retorna a exceção como texto
     */
    public static String toString(Throwable e) {

        if (e == null) {

            return "";
        }

        StringWriter str = new StringWriter();
        PrintWriter writer = new PrintWriter(str);

        try {

            e.printStackTrace(writer);
            return str.getBuffer().toString();

        } finally {

            try {

                str.close();
                writer.close();

            } catch (IOException ex) {

                System.err.println(ex.toString());
            }
        }
    }
}
