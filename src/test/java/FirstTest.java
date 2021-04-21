import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {
    protected static WebDriver chromeDriver;
    private static Logger logger = LogManager.getLogger(FirstTest.class);
    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @BeforeClass
    public static void setTest() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        logger.info("Создаем Chrome-драйвер");
    }

    @Test
    public void openPage() {
        String title = chromeDriver.getTitle();
        chromeDriver.get(cfg.url());
        logger.info("Открыли url {}", cfg.url());
        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", chromeDriver.getTitle());
        logger.info("title страницы {}",title);
    }

    @AfterClass
    public static void clearTest() {
        logger.info("Закрываем Chrome-драйвер");
        if (chromeDriver != null)
            chromeDriver.quit();
    }
}
