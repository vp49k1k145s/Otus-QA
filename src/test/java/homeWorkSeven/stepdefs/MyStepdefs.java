package homeWorkSeven.stepdefs;

import config.ServerConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class MyStepdefs {

    private static Logger logger = LogManager.getLogger(MyStepdefs.class);
    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private static WebDriver chromeDriver;

    @Before
    public static void setTest() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        logger.info("Создаем Chrome-драйвер");
    }

    @After
    public static void clearTest() {
        logger.info("Закрываем Chrome-драйвер");
        if (chromeDriver != null)
            chromeDriver.quit();
    }

    @Дано("Пользователь открывает вкладку кондакты")
    public void openPageContact() {
        chromeDriver.get("https://otus.ru");
        chromeDriver.findElement(By.xpath("/html/body/div[1]/div/footer/div/div/div[1]/div/div[1]/div/div[1]/div/div[4]/a"))
                .click();
    }

    @Когда("он проверяет адрес")
    public void checkAdress() {
        WebElement element = chromeDriver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[4]/div[1]/div[3]/div[2]"));
        Assert.assertEquals("125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02", element.getText());

    }

    @Тогда("разворачивает окно браузера на полный экран")
    public void expandsWindow() {
        chromeDriver.manage().window().maximize();
    }

    @Дано("Пользователь открывает сайт otus.")
    public void openWebsite(){
        chromeDriver.get("https://otus.ru");
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Когда("он заполняет тестовый почтовый ящик")
    public void completeTestEmail(){
        String email = "mail@mail.ru";
        chromeDriver.findElement(By.xpath("/html/body/div[1]/div/footer/div/div/div[1]/div/div[3]/form/input[2]"))
                .sendKeys(email);
    }

    @Тогда("он нажимает кнопку Подписаться")
    public void pushButton(){
        WebElement button = chromeDriver.findElement(By.xpath("/html/body/div[1]/div/footer/div/div/div[1]/div/div[3]/form/button"));
        ExpectedConditions.visibilityOf(button);
        button.click();
    }

    @И("Проверяет что появилось сообщение вы успешно подписались")
    public void checksMessage(){
        WebElement element2 = chromeDriver.findElement(By.cssSelector("p.subscribe-modal__success"));
        Assert.assertEquals("Вы успешно подписались", element2.getText());
        logger.info("Текст {}", element2.getText());
    }
}