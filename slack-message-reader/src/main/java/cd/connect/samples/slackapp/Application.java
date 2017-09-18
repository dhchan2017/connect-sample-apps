package cd.connect.samples.slackapp;

import cd.connect.samples.slackapp.api.SlackMessageyApiModule;
import cd.connect.samples.slackapp.api.jaxrs.JaxrsClientManager;
import cd.connect.samples.slackapp.api.jaxrs.JaxrsClientModule;
import org.springframework.context.annotation.Import;

@Import({SlackAppGenConfig.class, JerseyDataConfig.class, SlackMessageyApiModule.class})
public class Application extends BaseApplication {


}
