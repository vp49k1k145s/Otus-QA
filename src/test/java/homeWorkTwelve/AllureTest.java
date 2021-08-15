package homeWorkTwelve;

import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;

@Epic("Allure для Selenium")
@Owner(value = "Дзюбенко Вадим")
public class AllureTest {
    private static Logger logger = LogManager.getLogger(AllureTest.class);
    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private static WebDriver chromeDriver;

    @BeforeClass
    @DisplayName("Создание драйвера")
    @Story("ChromeDriver")
    public static void setTest() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        logger.info("Создаем Chrome-драйвер");
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        chromeDriver.manage().window().maximize();
    }

    @AfterClass
    @DisplayName("Закрываем Chrome-драйвер")
    @Story("ChromeDriver")
    public static void clearTest() {
        logger.info("Закрываем Chrome-драйвер");
        if (chromeDriver != null)
            chromeDriver.quit();
    }

    @Test
    @Step("Проверка стараницы контакты")
    @Story("Проверка контактов")
    @Description("Открываем сайт Otus и переход на страницу контакты")
    @DisplayName("Проверка страницы контакты")
    public void oneTest() {
        chromeDriver.get(cfg.url());
        logger.info("Открыли url {}", cfg.url());
        chromeDriver.findElement(By.xpath("/html/body/div[1]/div/footer/div/div/div[1]/div/div[1]/div/div[1]/div/div[4]/a"))
                .click();
        WebElement element = chromeDriver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[4]/div[1]/div[3]/div[2]"));
        Assert.assertEquals("125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02", element.getText());
        logger.info("Адрес {}", element.getText());
        Allure.addAttachment("Открытие сайта", new ByteArrayInputStream(((TakesScreenshot) chromeDriver).getScreenshotAs(OutputType.BYTES)));
        Assert.assertEquals("Контакты | OTUS", chromeDriver.getTitle());
        logger.info("title страницы {}", chromeDriver.getTitle());
    }


    @Test
    @Step("tele2")
    @Story("Поиск номеров телефонов")
    @Description("Открываем сайт tele2")
    @DisplayName("Поиск номеров телефонов начинающихся с 97")
    public void twoTest() {
        String url = "https://msk.tele2.ru/shop/number";
        chromeDriver.get(url);
        logger.info("Открыли url {}", url);
        chromeDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        chromeDriver.findElement(By.cssSelector("#searchNumber"))
                .sendKeys("97");
        ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/div/div/div[2]/div[1]/div/div/div/div[3]/span/div"));
        Allure.addAttachment("Cтраница Контакты. Title", new ByteArrayInputStream(((TakesScreenshot) chromeDriver).getScreenshotAs(OutputType.BYTES)));
        logger.info("Телефонные номера отображены на странице");
    }


}
