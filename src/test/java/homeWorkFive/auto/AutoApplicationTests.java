package homeWorkFive.auto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

@SpringBootTest
class AutoApplicationTests {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    @Test
    void contextLoads() {
        Car flyingCar = (Car) context.getBean("experimentalCar");
        String result = flyingCar.readyForSale();
        Assert.isTrue(result.equals("Car Ready in: StrangeCar"), "Expected configuration -> " + result);
    }
}