package br.com.backend.view.util.message;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
public class MessageProperties {

    private static final MessageLoader loader = new MessageLoader();

    /**
     * 
     * @param key - entrar com a chave do bundle ex.: label.39jsur
     * @return - retorna o texto referente a chave passada
     */
    public static String getString(String key) {

        return (String) loader.getString(key);
    }
}
