package mum.waa.flight;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import mum.waa.model.Flight;

@ManagedBean(name = "bean")
@SessionScoped
public class BeanFlight {
	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String PASS = "sucess";
	private static final String FAIL = "fail";
	@ManagedProperty(value = "#{flightList}")
	private List<Flight> flightList;
	private String Message;
	@ManagedProperty(value = "#{searchString}")
	private String searchString = "";
	
	public String getSearchString() {
		return searchString;
	}
	


	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public void search(){
	System.out.println("Search");
	System.out.println(searchString);
	}
	public void filterAirline(AjaxBehaviorEvent event) {
		if (searchString.isEmpty()) {
			Client client = ClientBuilder.newClient();
			List<Flight> a = client.target("http://localhost:8080/airlinesWebApp/rs/flight/findAll")
					.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Flight>>() {
					});
			flightList = a;
		} else {
			Client client = ClientBuilder.newClient();
			System.out.println(searchString);
			List<Flight> a = client.target("http://localhost:8080/airlinesWebApp/rs/flight/findByAirline?airline="+searchString)
					.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Flight>>() {
					});
			
			System.out.println(a.size());
//	
			flightList = a;
		}
	}

	public List<Flight> getAllFlight() {
		Client client = ClientBuilder.newClient();
		flightList = client.target("http://localhost:8080/airlinesWebApp/rs/flight/findAll")
				.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Flight>>() {
				});

		return flightList;
	}

	
	public String getFlights() {
		Client client = ClientBuilder.newClient();
		List<Flight> a = client.target("http://localhost:8080/airlinesWebApp/rs/flight/findAll")
				.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Flight>>() {
				});
		flightList = a;
		return "/flights.xhtml?faces-redirect=true";
	}

	public void create() throws ClientErrorException {
		System.out.println("!!!!!!!!!!!!!!!!!!!11111");
		Form form = new Form();

		form.param("flightnr", "AA 07");
		form.param("departureDate", "17 Dec 2013");
		form.param("departureTime", "02:45 pm");
		form.param("arrivalDate", "17 Dec 2013");
		form.param("arrivalTime", "02:45 pm");
		form.param("airline", "SkyTeam");
		form.param("origin", "Los Angeles International");
		form.param("destination", "London Heathrow");
		form.param("airplane", "12345");

		Client client = ClientBuilder.newClient();

		String callResult = client.target("http://localhost:8080/airlinesWebApp/rs/flight/createFlight")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
		System.out.println("result:");
		System.out.println(callResult);
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public List<Flight> getFlightList() {
		return flightList;
	}

	public void setFlightList(List<Flight> flightList) {
		this.flightList = flightList;
	}

}
