package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@Path("person")
public class RESTQuote {

    private static Map<Integer, String> quotes = new HashMap() {
        {
//            put(1, "Friends are kisses blown to us by angels");
//            put(2, "Do not take life too seriously. You will never get out of it alive");
//            put(3, "Behind every great man, is a woman rolling her eyes");
        }
    };
    @Context
    private UriInfo context;

    @Context
    private HttpHeaders headers;

    public RESTQuote() {
    }
    //for testing
    public static void main(String[] args) throws QouteNotFoundExeption {
        System.out.println(new RESTQuote().getQoute());
        System.out.println(new RESTQuote().deleteQuote(1));
        System.out.println(new RESTQuote().deleteQuote(1));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("random")
    public String getQoute() throws QouteNotFoundExeption {
        JsonObject quote = new JsonObject();
        if(quotes.isEmpty()){
            
            quote.addProperty("quote", "{code: 404, message: No Quotes Created yet}");
            String jsonreponse = new Gson().toJson(quote);
            return jsonreponse;
        }
        int key = new Random().nextInt(quotes.size())+1;
        quote.addProperty("quote", quotes.get(key));
        String jsonreponse = new Gson().toJson(quote);
        return jsonreponse;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String getQuote(@PathParam("id") int id, @DefaultValue("None") @QueryParam("job") String job) throws QouteNotFoundExeption{
        JsonObject quote = new JsonObject();
        String temp = quotes.get(id);
        if(temp == null||temp.equals("")){
            
            quote.addProperty("quote", ("{code: 404, message: Quote with requested id not found}"));
            String jsonreponse = new Gson().toJson(quote);
            return jsonreponse;
        }
        quote.addProperty("quote",temp );
        String jsonreponse = new Gson().toJson(quote);
        return jsonreponse;
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("all")
//    public String postQoute() {
//        JsonObject quote = new JsonObject();
//        int key = new Random().nextInt(quotes.size()-1);
//        quote.addProperty("quote", quotes.get(key));
//        String jsonreponse = new Gson().toJson(quote);
//        return "{\"persons\":["+jsonreponse+"]}";
//    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postQoute(String content) {
        JsonObject quote = new JsonObject();
        String jo = new JsonParser().parse(content).getAsString();
        int temp = quote.size();
        quote.addProperty("id", temp );
        quotes.put(temp, jo);
        quote.addProperty("quote", quotes.get(temp)+1);
        String jsonreponse = new Gson().toJson(quote);
        return jsonreponse;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String putQuote(@PathParam("id") int id, String content) throws QouteNotFoundExeption{
        JsonObject quote = new JsonObject();
        String jo = new JsonParser().parse(content).getAsString();
        if(quotes.get(id)==null||quotes.get(id).equals("")){
            quote.addProperty("quote", "{code: 404, message: Quote with requested id not found}");
            String jsonreponse = new Gson().toJson(quote);
            return jsonreponse;
        }
        int temp = id;
        quote.addProperty("id", temp );
        quotes.put(temp, jo);
        quote.addProperty("quote", quotes.get(temp-1));
        String jsonreponse = new Gson().toJson(quote);
        return jsonreponse;
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String deleteQuote(@PathParam("id") int id) throws QouteNotFoundExeption{
        JsonObject quote = new JsonObject();
        if(quotes.get(id)==null||quotes.get(id).equals("")){
            quote.addProperty("quote", "{code: 404, message: Quote with requested id not found}");
            String jsonreponse = new Gson().toJson(quote);
            return jsonreponse;
        }
        int temp = id;
        quote.addProperty("quote", quotes.get(temp));
        quotes.remove(id);
        String jsonreponse = new Gson().toJson(quote);
        return jsonreponse;
    }
}
