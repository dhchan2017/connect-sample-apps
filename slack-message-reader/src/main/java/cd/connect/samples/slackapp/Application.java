package cd.connect.samples.slackapp;

import cd.connect.samples.slackapp.api.SlackMessageyApiModule;
import cd.connect.samples.slackapp.bootstrap.ElasticsearchClientConfiguration;
import org.springframework.context.annotation.Import;

@Import({SlackAppGenConfig.class, JerseyDataConfig.class, SlackMessageyApiModule.class, ElasticsearchClientConfiguration.class})
public class Application extends BaseApplication {


}
