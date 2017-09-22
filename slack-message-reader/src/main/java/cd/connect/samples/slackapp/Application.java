package cd.connect.samples.slackapp;

import cd.connect.samples.slackapp.bootstrap.ElasticsearchClientConfiguration;
import cd.connect.spring.jersey.BaseWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SlackAppGenConfig.class, JerseyDataConfig.class,
  ElasticsearchClientConfiguration.class})
public class Application extends BaseWebApplication {
}
