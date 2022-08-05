package ru.yandex.practicum.stellarburgers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.stellarburgers.PageObject.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static org.junit.Assert.assertEquals;

public class MainPageTest {

    MainPage mainPage = Selenide.page(MainPage.class);

    @Before
    public void setup() {
        //настройка для запуска тестов в YandexBrowser
        /*System.setProperty("webdriver.chrome.driver", "./src/main/resources/yandexdriver");
        WebDriver driver = new ChromeDriver();
        setWebDriver(driver);*/

        mainPage = open(MainPage.URL, MainPage.class);
    }

    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Проверка перехода в список Булок")
    public void checkClickTabButtonBun() {
        mainPage.clickSauceButton();
        mainPage.clickFillingButton();
        mainPage.clickBunButton();
        assertEquals("Переход на вкладку Булки не осуществлен", "Булки", mainPage.getCurrentButtonText());
    }

    @Test
    @DisplayName("Проверка перехода в список Соусов")
    public void checkClickTabButtonSauce() {
        mainPage.clickFillingButton();
        mainPage.clickBunButton();
        mainPage.clickSauceButton();
        assertEquals("Переход на вкладку Соусов не осуществлен", "Соусы", mainPage.getCurrentButtonText());
    }

    @Test
    @DisplayName("Проверка перехода в список Начинок")
    public void checkClickTabButtonFilling() {
        mainPage.clickSauceButton();
        mainPage.clickBunButton();
        mainPage.clickFillingButton();
        assertEquals("Переход на вкладку Начинки не осуществлен", "Начинки", mainPage.getCurrentButtonText());
    }
}
