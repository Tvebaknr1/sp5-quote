package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class QuoteNotFoundExceptionMapper implements ExceptionMapper<QouteNotFoundExeption>
{
    @Context
    ServletContext context;
 
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(QouteNotFoundExeption e)
    {
        boolean isDebug = context.getInitParameter("debug").equals("true");

        ErrorMessage err = new ErrorMessage(e, 404, isDebug);
        err.setDescription("Tried to call ...");
        
        return Response.status(404).entity(gson.toJson(err)).type(MediaType.APPLICATION_JSON).build();
    }
}