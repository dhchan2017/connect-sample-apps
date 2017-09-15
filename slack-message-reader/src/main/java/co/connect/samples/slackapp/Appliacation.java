package co.connect.samples.slackapp;

import co.connect.samples.slackapp.api.SlackMessageyApiModule;
import org.springframework.context.annotation.Import;

@Import({SlackMessageyApiModule.class})
public class Appliacation extends BaseApplication {


}
