package cd.connect.samples.slackapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;

public class MessageListener implements SlackMessagePostedListener {

    Logger log = LoggerFactory.getLogger(MessageListener.class);

    Publisher publisher = new Publisher();
    ObjectMapper mapper = new ObjectMapper();

    MessageListener() throws JMSException {
        publisher.create("slackListener", "slack-messages");
    }

    public void onEvent(SlackMessagePosted slackMessagePosted, SlackSession slackSession) {
        try {
            ObjectNode jsonNodes = mapper.valueToTree(slackMessagePosted);
            jsonNodes.replace("channel", mapper.valueToTree(slackMessagePosted.getChannel()));
            jsonNodes.replace("sender", mapper.valueToTree(slackMessagePosted.getSender()));
            publisher.sendMessgae(mapper.writeValueAsString(jsonNodes));
        } catch (JsonProcessingException | JMSException e) {
            log.info("unable to parse message:"+slackMessagePosted.getMessageContent());
        }
    }
}
