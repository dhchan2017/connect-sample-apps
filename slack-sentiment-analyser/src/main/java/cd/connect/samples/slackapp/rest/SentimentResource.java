package cd.connect.samples.slackapp.rest;

import cd.connect.samples.slackapp.api.Channel;
import cd.connect.samples.slackapp.api.SentimentService;
import cd.connect.samples.slackapp.api.Sentimentsummary;
import cd.connect.samples.slackapp.dao.SentimentDao;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.ArrayList;

@Singleton
public class SentimentResource implements SentimentService{

    @Inject
    SentimentDao sentimentDao;

    @Override
    public Sentimentsummary gETSentiment() {

        Sentimentsummary sentimentsummary = new Sentimentsummary();
        ArrayList<Channel> channels = new ArrayList<>();
        sentimentDao.getSentiment().entrySet().forEach(stringListEntry -> {
            Channel e = new Channel();
            e.setChannel(stringListEntry.getKey());
            e.setMessageCount(new BigDecimal(stringListEntry.getValue().size()));
            e.setSentiment(new BigDecimal(stringListEntry.getValue().size() % 10));
            channels.add(e);
        });
        sentimentsummary.setChannels(channels);
        return sentimentsummary;
    }
}
