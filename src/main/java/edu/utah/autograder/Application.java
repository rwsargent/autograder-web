package edu.utah.autograder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("edu.utah.autograder.entities")
@EnableJpaRepositories("edu.utah.autograder.repositories")
@ComponentScan ("edu.utah.autograder.*")
public class Application {
    public static void main(String[] args) throws Exception {
       SpringApplication.run(Application.class, args);
    }
}
