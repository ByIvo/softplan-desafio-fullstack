package rocks.byivo.processmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("rocks.byivo.processmanager")
public class ProcessManagerApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProcessManagerApplication.class);
    }
    
    public static void main(String[] args) {
	SpringApplication.run(ProcessManagerApplication.class, args);
    }
    
}
