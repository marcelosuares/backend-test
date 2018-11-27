package br.com.backend.view.util.message;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
public class MessageGrowl {

    /**
     * lança a mensagem de erro
     *
     * @param msg - entrar com a mensagem
     */
    public static void error(String msg) {

        Severity severity = FacesMessage.SEVERITY_ERROR;
        message(severity, null, msg);
    }

    /**
     * lança a mensagem de informação
     *
     * @param msg - entrar com a mensagem
     */
    public static void info(String msg) {

        Severity severity = FacesMessage.SEVERITY_INFO;
        message(severity, null, msg);
    }

    /**
     * lança a mensagem de alerta
     *
     * @param msg - entrar com a mensagem
     */
    public static void warn(String msg) {

        Severity severity = FacesMessage.SEVERITY_WARN;
        message(severity, null, msg);
    }

    /**
     * lança a mensagem de falha
     *
     * @param msg - entrar com a mensagem
     */
    public static void fatal(String msg) {

        Severity severity = FacesMessage.SEVERITY_FATAL;
        message(severity, null, msg);
    }

    /**
     * Monta a messagem completa
     *
     * @param severity - entrar com o tipo de mensagem
     * @param summary - entrar com o sumário da mensagem
     * @param detail - entrar com os detalhes da mensagem
     */
    public static void message(Severity severity, String summary, String detail) {

        FacesMessage fm = new FacesMessage();
        fm.setSeverity(severity);
        fm.setDetail("");
        fm.setSummary("");

        if (summary != null && !summary.equals("")) {

            fm.setSummary(summary);
        }

        if (detail != null && !detail.equals("")) {

            fm.setDetail(detail);
        }

        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    /**
     * messagem com sumário fixo
     *
     * @param severity - entrar com o tipo de mensagem
     * @param message - entar com a mensagem a ser apresentada
     * @return - retorna um FacesMessage com a mensagem
     */
    public static FacesMessage message(Severity severity, String message) {

        FacesMessage facesMessage = new FacesMessage(severity,
                getTitle(severity), message);
        return facesMessage;
    }

    /**
     *
     * @param severity - entrar com o tipo de mensagem
     * @return - retorna o título da mensagem sendo (Sucesso, Atenção, Erro,
     * Falha)
     */
    private static String getTitle(Severity severity) {
        String summary = "";

        if (severity.equals(FacesMessage.SEVERITY_INFO)) {

            summary = MessageProperties.getString("label.54e43tt8");

        } else if (severity.equals(FacesMessage.SEVERITY_WARN)) {

            summary = MessageProperties.getString("label.03zebatl");

        } else if (severity.equals(FacesMessage.SEVERITY_ERROR)) {

            summary = MessageProperties.getString("label.7oi332yb");

        } else if (severity.equals(FacesMessage.SEVERITY_FATAL)) {

            summary = MessageProperties.getString("label.5sq8m2f3");
        }

        return summary;
    }
}
