import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {
    protected static WebDriver chromeDriver;
    private static Logger logger = LogManager.getLogger(FirstTest.class);
    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @BeforeClass
    public static void setTestEnvironment() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        logger.debug("Подключен Chrome-драйвер");
    }

    @Test
    public void openPage1() {
        chromeDriver.get(cfg.url());
        logger.debug("Открыли URI в Chrome-браузере");
        logger.debug("Получили <title>: " + chromeDriver.getTitle());
    }

    @AfterClass
    public static void clearTestEnvironment() {
        logger.debug("Отключаем Chrome-драйвер");
        if (chromeDriver != null)
            chromeDriver.quit();
    }
}
