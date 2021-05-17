package homeWorkTwo;

import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class HomeWorkTwo {
    private static Logger logger = LogManager.getLogger(HomeWorkTwo.class);
    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private static WebDriver chromeDriver;

    @BeforeClass
    public static void setTest() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        logger.info("Создаем Chrome-драйвер");
    }

    @AfterClass
    public static void clearTest() {
        logger.info("Закрываем Chrome-драйвер");
        if (chromeDriver != null)
            chromeDriver.quit();
    }

    @Test
    public void oneTest() {
        chromeDriver.get(cfg.url());
        logger.info("Открыли url {}", cfg.url());
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        chromeDriver.findElement(By.xpath("/html/body/div[1]/div/footer/div/div/div[1]/div/div[1]/div/div[1]/div/div[4]/a"))
                .click();
        WebElement element = chromeDriver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[4]/div[1]/div[3]/div[2]"));
        Assert.assertEquals("125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02", element.getText());
        logger.info("Адрес {}", element.getText());
        chromeDriver.manage().window().maximize();
        Assert.assertEquals("Контакты | OTUS", chromeDriver.getTitle());
        logger.info("title страницы {}", chromeDriver.getTitle());
    }

    @Test
    public void thirdTest() {
        String text = "Программу курса в сжатом виде можно увидеть на странице курса после блока с преподавателями. Подробную программу курса можно скачать кликнув на “Скачать подробную программу курса”";
        chromeDriver.get(cfg.url());
        logger.info("Открыли url {}", cfg.url());
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        chromeDriver.findElement(By.xpath("/html/body/div[1]/div/footer/div/div/div[1]/div/div[1]/div/div[1]/div/div[6]/a"))
                .click();
        chromeDriver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/div[4]/div[1]"))
                .click();
        WebElement element1 = chromeDriver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/div[4]/div[2]"));
        Assert.assertEquals(text, element1.getText());
        logger.info("Текст {}", text);
    }

    @Test
    public void fourthTest() {
        chromeDriver.get(cfg.url());
        logger.info("Открыли url {}", cfg.url());
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String email = "mail@mail.ru";
        chromeDriver.findElement(By.xpath("/html/body/div[1]/div/footer/div/div/div[1]/div/div[3]/form/input[2]"))
                .sendKeys(email);
        logger.info("Введен email {}", email);
        WebElement button = chromeDriver.findElement(By.xpath("/html/body/div[1]/div/footer/div/div/div[1]/div/div[3]/form/button"));
        ExpectedConditions.visibilityOf(button);
        button.click();
        WebElement element2 = chromeDriver.findElement(By.cssSelector("p.subscribe-modal__success"));
        Assert.assertEquals("Вы успешно подписались", element2.getText());
        logger.info("Текст {}", element2.getText());
    }
}
