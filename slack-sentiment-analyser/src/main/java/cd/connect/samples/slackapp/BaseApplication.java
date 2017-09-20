package cd.connect.samples.slackapp;

import cd.connect.jersey.client.JaxrsClientManager;
import cd.connect.spring.jersey.log.JerseyFilteringConfiguration;
import cd.connect.spring.servlet.ServletModuleRegistration;
import com.bluetrainsoftware.common.config.EnableStickyConfiguration;
import com.bluetrainsoftware.common.config.PreStartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Configuration
@EnableStickyConfiguration
@Import({JerseyFilteringConfiguration.class, ServletModuleRegistration.class, JaxrsClientManager.class})
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

        context.register(this.getClass());

        log.info("refreshing now");
        context.refresh();
        log.info("refresh complete, starting pre-start");

        context.getBean(PreStartRepository.class).start();
        log.info("pre-start complete, starting webserver.");

        // now tell the servlet context about the refreshed context
        sce.getServletContext().setAttribute(APPL_CTX, context);
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
