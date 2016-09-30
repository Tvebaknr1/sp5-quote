package rest;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses()
    {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources)
    {
        resources.add(rest.NotFoundExceptionMapper.class);
        resources.add(rest.QuoteNotFoundExceptionMapper.class);
        resources.add(rest.RESTQuote.class);
        resources.add(rest.RuntimeExceptionMapper.class);
    }
}
