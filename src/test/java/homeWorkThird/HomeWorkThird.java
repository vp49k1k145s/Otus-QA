package homeWorkThird;

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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomeWorkThird {
    private static Logger logger = LogManager.getLogger(HomeWorkThird.class);
    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private static WebDriver chromeDriver;

    @BeforeClass
    public static void setTest() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        logger.info("Создаем Chrome-драйвер");
        chromeDriver.manage().window().maximize();
    }

    @AfterClass
    public static void clearTest() {
        logger.info("Закрываем Chrome-драйвер");
        if (chromeDriver != null)
            chromeDriver.quit();
    }

    @Test
    public void marketTest() {
        chromeDriver.get(cfg.url1());
        logger.info("Открыли url {}", cfg.url1());

        WebDriverWait wait = new WebDriverWait(chromeDriver, 10);
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        chromeDriver.findElement(By.xpath("//a[starts-with(@href,'/catalog--elektronika/')]"))
                .click();
        logger.info("Открыт раздел Электроника");
        chromeDriver.findElement(By.xpath("//a[starts-with(@href, '/catalog--smartfony/')]"))
                .click();
        logger.info("Открыт раздел Смартфоны");

        WebElement manufacturerXiaomi = chromeDriver.findElement(By.xpath("//input[@name='Производитель Xiaomi']"));
        Actions actions = new Actions(chromeDriver);
        actions.moveToElement(manufacturerXiaomi).click().perform();

        WebElement manufacturerSamsung = chromeDriver.findElement(By.xpath("//input[@name='Производитель Samsung']"));
        Actions actions2 = new Actions(chromeDriver);
        actions2.moveToElement(manufacturerSamsung).click().perform();
        ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[3]/div[3]/div[4]/div/div[1]/div/div[1]/div[2]/div/div"));

        chromeDriver.findElement(By.xpath("//button[@data-autotest-id='dprice']")).click();
        logger.info("Выполнение сортировки по цене");
        chromeDriver.navigate().refresh();

        String firstSamsungName = chromeDriver.findElement(By.xpath("//a[not(contains(@href,'premiumOffers')) and not(contains(@rel,'nofollow noopener')) and contains(.,'Samsung')]/span")).getText();
        String[] shortNames = firstSamsungName.split(",");
        String messageExpectedSamsung = "Товар " + shortNames[0] + " добавлен к сравнению";
        logger.info(messageExpectedSamsung);

        String firstXiaomiName = chromeDriver.findElement(By.xpath("//a[not(contains(@href,'premiumOffers')) and not(contains(@rel,'nofollow noopener')) and contains(.,'Xiaomi')]/span")).getText();
        String[] shortXiNames = firstXiaomiName.split(",");
        String messageExpectedXiaomi = "Товар " + shortXiNames[0] + " добавлен к сравнению";
        logger.info(messageExpectedXiaomi);

        WebElement addFirstSamsung = chromeDriver.findElement(By.xpath("//article[contains(.,'Samsung')and contains(@data-autotest-id,'product-snippet')]/descendant::div[contains(@aria-label,'сравнению')]"));
        addFirstSamsung.click();
        String s = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'добавлен к сравнению')]"))).getText();
        Assert.assertEquals(messageExpectedSamsung, s);

        WebElement addFirstXiaomi = chromeDriver.findElement(By.xpath("//article[contains(.,'Xiaomi') and contains(@data-autotest-id,'product-snippet')]/descendant::div[contains(@aria-label,'сравнению')]"));
        addFirstXiaomi.click();
        s = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'добавлен к сравнению')]"))).getText();
        Assert.assertEquals(messageExpectedXiaomi, s);

        WebElement we = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[(contains(.,'Сравнить'))]")));
        we.sendKeys(Keys.ENTER);

        List<WebElement> list = chromeDriver.findElements(By.xpath("//a[(contains(.,'Смартфон '))]"));
        logger.info("Найдено " + list.size() + " товара");
        Assert.assertEquals(list.size(), 2);
    }
}
