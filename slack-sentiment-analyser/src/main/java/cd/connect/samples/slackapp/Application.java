package cd.connect.samples.slackapp;

import cd.connect.samples.slackapp.api.SlackSentimentAnalyserApiModule;
import cd.connect.samples.slackapp.subscription.SlackMessageConsumer;
import org.springframework.context.annotation.Import;

@Import({SlackAppGenConfig.class, JerseyDataConfig.class, SlackSentimentAnalyserApiModule.class, SlackMessageConsumer.class})
public class Application extends BaseApplication{
}
