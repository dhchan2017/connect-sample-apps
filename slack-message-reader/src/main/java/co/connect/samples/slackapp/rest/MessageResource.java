package co.connect.samples.slackapp.rest;

import co.connect.samples.slackapp.api.Messagelist;
import co.connect.samples.slackapp.api.MessagesApi;
import co.connect.samples.slackapp.api.Messagesquery;

import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;

@Singleton
public class MessageResource implements MessagesApi{
    public Messagelist gETMessages(String authorization, Messagesquery body) throws NotFoundException {
        return null;
    }
}
