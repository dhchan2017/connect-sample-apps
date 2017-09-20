package cd.connect.samples.slackapp.subscription;

import cd.connect.samples.slackapp.dao.SentimentDao;
import cd.connect.samples.slackapp.model.SlackMessage;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.stickycode.stereotype.configured.PostConfigured;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.inject.Inject;
import javax.jms.*;
import java.io.IOException;

public class SlackMessageConsumer implements MessageListener {

    @Inject
    SentimentDao sentimentDao;
    ObjectMapper mapper = new ObjectMapper();

    public SlackMessageConsumer(){
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @PostConfigured
    public void init() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic("slack-messages");

        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(this);
    }

    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            try {
                String text = ((TextMessage) message).getText();
                System.out.println("text:"+text);
                sentimentDao.addMessage(mapper.readValue(text, SlackMessage.class));
            } catch (JMSException | IOException e) {
                e.printStackTrace();
            }
        }

    }
}
