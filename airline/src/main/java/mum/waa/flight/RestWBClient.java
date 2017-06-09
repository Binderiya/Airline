package mum.waa.flight;
import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
public class RestWBClient {
	static final String REST_URI = "localhost:8080/airlinesWebApp/rs/flight/findAll";
    public static void main(String[] args) {
    	ClientConfig config = new DefaultClientConfig();   
        Client client = Client.create(config);
        WebResource service = client.resource(REST_URI);
        WebResource msgService = service.path("10");
        String msg = msgService.accept(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(msg);
    }
}
