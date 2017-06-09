package mum.waa.flight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;



import mum.waa.model.Flight;

@ManagedBean(name="flightBean")
@SessionScoped
public class FlightBean implements Serializable {
	
	List<Flight> flightList;
	private static final long serialVersionUID = 1L;

	
	public List<Flight> getAllFlights(){
        Client client = ClientBuilder.newClient();
        flightList =client.target("http://localhost:8080/airlinesWebApp/rs/flight/findAll")
        		.request(MediaType.APPLICATION_JSON)
        		.get(new GenericType<List<Flight>>() {});
		return flightList;
	}
	
	


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
