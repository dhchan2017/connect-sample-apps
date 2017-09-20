package cd.connect.samples.slackapp.api.config;


import java.util.HashMap;
import java.util.Map;

public final class ConfigConst {
    private static Map<String, String> urls = new HashMap<>();

    public static String url(String key) {
        if (key.startsWith("http")) {
            return key;
        }
        String url = System.getProperty(key);
        return (url == null) ? urls.get(key) : url;
    }

    /**
     * Instantiates a new cd.connect.samples.slackapp.api.config const.
     */
    private ConfigConst() {

    }

    /**
     * The Constant MESSAGE_SERVICES_KEY.
     */
    public static final String MESSAGE_SERVICES_KEY = "sentiment.services";
    private static final String MESSAGE_SERVICES_URL = "http://sentiment-service:9090/data";



    /**
     * These will be defaults if there is no externally configured url. In Kubernetes they should be the urls that
     * are used.
     */
    static {
        urls.put(MESSAGE_SERVICES_KEY, MESSAGE_SERVICES_URL);
    }
}
