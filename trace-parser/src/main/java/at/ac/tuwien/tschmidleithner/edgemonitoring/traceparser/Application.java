package at.ac.tuwien.tschmidleithner.edgemonitoring.traceparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("at.ac.tuwien.tschmidleithner.edgemonitoring.*")
@ComponentScan("at.ac.tuwien.tschmidleithner.edgemonitoring.*")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}