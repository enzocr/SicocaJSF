package cr.ac.una.icai.sicoca.utilidades;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;

public class Utilidades {

    public static String obtenerMsj(String key) {

        FacesContext context = FacesContext.getCurrentInstance();
        Locale locale = context.getViewRoot().getLocale();
        String messagesBaseName = "cr.ac.una.icai.sicoca.propiedades.etiquetas";

        if (locale == null || context == null) {
            locale = Locale.getDefault();
        } else {
            if (context.getViewRoot() != null) {
                locale = context.getViewRoot().getLocale();
            }
        }
        String resourceString;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(messagesBaseName,
                    locale);
            resourceString = bundle.getString(key);
        } catch (MissingResourceException e) {
            return key;
        }

        return resourceString;
    }
}
