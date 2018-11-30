package br.com.backend.api.ws.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
@javax.ws.rs.ApplicationPath("api/ws")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        
         resources.add(br.com.backend.api.ws.rest.provider.CompanyProvider.class);
         resources.add(br.com.backend.api.ws.rest.provider.CoinProvider.class);
    }
}
