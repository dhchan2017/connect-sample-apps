package cd.connect.samples.slackapp.api.jaxrs;

import javax.ws.rs.client.Client;

public interface JaxrsClient {
  Client getClient();
}
