package co.connect.samples.slackapp;

import com.bluetrainsoftware.common.config.PreStartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Arrays;

public class BaseApplication implements ServletContextListener {
    protected static final Logger log = LoggerFactory.getLogger(BaseApplication.class);
    protected AnnotationConfigWebApplicationContext context;
    protected ServletContext servletContext;
    public static final String APPL_CTX = "spring.applicationcontext";

    public static final String REST_SERVICES_URL_PATTERN = "/data/*";
    public static final String REST_SERVICES_ADMIN_URL_PATTERN = "/admin/data/*";



    public void contextInitialized(ServletContextEvent sce) {
        this.servletContext = sce.getServletContext();

        context = new AnnotationConfigWebApplicationContext();
        context.setServletContext(sce.getServletContext());

        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);


        String registrationClass = System.getProperty("spring.registrationClasses");

        if (registrationClass != null) {
            Arrays.stream(registrationClass.split(",")).map(String::trim).filter(s -> s.length() > 0).forEach(s -> {
                        try {
                            context.register(Class.forName(s));
                        } catch (ClassNotFoundException e) {
                            log.error("Unable to find registration class `{}`", s);
                            throw new RuntimeException(e);
                        }
                    }
            );
        } else {
            context.register(this.getClass());
        }


        registerOtherModules();

        log.info("refreshing now");
        context.refresh();
        log.info("refresh complete, starting pre-start");

        context.getBean(PreStartRepository.class).start();
        log.info("pre-start complete, starting webserver.");

        // now tell the servlet context about the refreshed context
        sce.getServletContext().setAttribute(APPL_CTX, context);
    }

    protected void registerOtherModules() {
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
