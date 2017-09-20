package cd.connect.samples.slackapp;

import com.bluetrainsoftware.bathe.initializer.BatheTimeWatcher;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;

import javax.jms.JMSException;
import java.io.IOException;

public class SlackListenerApplication {

    public static void main(String[] args) throws IOException, JMSException {

        System.out.println("starting slack channel listener....");
        new BatheTimeWatcher().startWatching();
        SlackSession session = SlackSessionFactory.createWebSocketSlackSession(System.getProperty("slack.authToken"));
        session.addMessagePostedListener(new MessageListener());
        session.connect();
    }
}
