package cd.connect.service;


import cd.connect.samples.slackapp.api.MessagesService;
import cd.connect.samples.slackapp.api.SentimentService;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.filter.LoggingFilter;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.HashMap;


public class ApiService {


	private Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
	private WebTarget webTarget = client.target(System.getProperty("services.api"));

	public MessagesService messagesApi() {
		return WebResourceFactory.newResource(MessagesService.class, webTarget);
	}

	public SentimentService sentimentApi() {
		return WebResourceFactory.newResource(SentimentService.class, webTarget);
	}

	public Response slackMessagePost(HashMap Text) {

		client = ClientBuilder.newClient();

		WebTarget webTarget = client.target(UriBuilder.fromPath(System.getProperty("services.url")).build());

		try {
			Response response = webTarget.request().post(Entity.entity(Text, MediaType.APPLICATION_JSON));
			System.out.println(response.readEntity(String.class));

			return response;

		} catch (Exception e) {
			System.out.println("Failed to get response");

			throw e;
		}
	}

}
