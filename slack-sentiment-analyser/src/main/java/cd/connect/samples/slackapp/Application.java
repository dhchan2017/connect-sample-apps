package cd.connect.samples.slackapp;

import cd.connect.samples.slackapp.subscription.SlackMessageConsumer;
import cd.connect.spring.jersey.BaseWebApplication;
import org.springframework.context.annotation.Import;

@Import({SlackAppGenConfig.class, JerseyDataConfig.class, SlackMessageConsumer.class})
public class Application extends BaseWebApplication {
}
