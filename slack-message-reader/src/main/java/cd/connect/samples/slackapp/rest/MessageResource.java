package cd.connect.samples.slackapp.rest;

import cd.connect.samples.slackapp.api.Messagelist;
import cd.connect.samples.slackapp.api.MessagesApi;

import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;
import java.math.BigDecimal;

@Singleton
public class MessageResource implements MessagesApi{


    @Override
    public Messagelist gETMessages(String authorization, String userId, BigDecimal fromDate, BigDecimal toDate) throws NotFoundException {
        return null;
    }
}
