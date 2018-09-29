package rocks.byivo.processmanager;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class ContextPathConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    public static final String BASE_PATH = "/process-manager";

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
	factory.setContextPath(BASE_PATH);
    }
    
}
