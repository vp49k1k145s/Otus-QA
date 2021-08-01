package homeWorkFive.auto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.auto")
public class SpringConfig {

    @Bean
    public ClassicConfiguration classicConfiguration() {
        return new ClassicConfiguration();
    }

    @Bean
    public SportConfiguration sportConfiguration() {
        return new SportConfiguration();
    }

    @Bean
    public ExperimentalConfiguration experimentalConfiguration() {
        return new ExperimentalConfiguration();
    }

    @Bean Car classicCar() {
        return new Car(classicConfiguration());
    }

    @Bean Car sportCar() {
        return new Car(sportConfiguration());
    }

    @Bean Car experimentalCar() {
        return  new Car(experimentalConfiguration());
    }
}