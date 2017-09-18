package cd.connect.samples.slackapp.api.jaxrs;

import cd.connect.spring.jersey.log.JerseyFiltering;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.message.internal.FormProvider;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * @author Richard Vowles - https://plus.google.com/+RichardVowles
 */
public class JaxrsClientManager implements JaxrsClient {
  protected Client client;

  public JaxrsClientManager(JerseyFiltering jerseyFiltering) {
    client = ClientBuilder.newClient();
    client.property(CommonProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
    client.property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);
    client.property(CommonProperties.JSON_PROCESSING_FEATURE_DISABLE, true);
    client.property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
    client.register(JacksonFeature.class);
    client.register(MultiPartFeature.class);
    client.register(FormProvider.class);

    if (jerseyFiltering != null) {
      jerseyFiltering.registerFilters(client);
    }
  }

  public Client getClient() {
    return client;
  }
}
