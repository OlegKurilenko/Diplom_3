package ru.yandex.practicum.stellarburgers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.stellarburgers.PageObject.LoginPage;
import ru.yandex.practicum.stellarburgers.PageObject.MainPage;
import ru.yandex.practicum.stellarburgers.PageObject.ProfilePage;
import ru.yandex.practicum.stellarburgers.PageObject.RegisterPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProfilePageTest {

    LoginPage loginPage = Selenide.page(LoginPage.class);
    RegisterPage registerPage = Selenide.page(RegisterPage.class);
    ProfilePage profilePage = Selenide.page(ProfilePage.class);
    MainPage mainPage = Selenide.page(MainPage.class);
    User user;

    @Before
    public void setup() {
        //настройка для запуска тестов в YandexBrowser
        /*System.setProperty("webdriver.chrome.driver", "./src/main/resources/yandexdriver");
        WebDriver driver = new ChromeDriver();
        setWebDriver(driver);*/

        user = User.getRandomCorrectUser();
        registerPage = open(RegisterPage.URL, RegisterPage.class);
        registerPage.setName(user.name);
        registerPage.setEmail(user.email);
        registerPage.setPassword(user.password);
        registerPage.clickRegisterButton();
        assertTrue("Переход на страницу входа не выполнен!", loginPage.onLoginPage());
    }

    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Проверка перехода по кнопке в \"Личый кабинет\"")
    public void checkLoginProfilePage() {
        mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLkButton();
        loginPage.onLoginPage();
        loginPage.setEmail(user.email);
        loginPage.setPassword(user.password);
        loginPage.clickLoginButton();
        assertTrue(mainPage.checkOrderButton());
        mainPage.clickLkButton();
        assertEquals(user.name, profilePage.getNameField());
        assertEquals(user.email.toLowerCase(), profilePage.getEmailField());
    }

    @Test
    @DisplayName("Проверка перехода по клику на «Конструктор»")
    public void checkTransferToConstructButton() {
        mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLkButton();
        loginPage.onLoginPage();
        loginPage.setEmail(user.email);
        loginPage.setPassword(user.password);
        loginPage.clickLoginButton();
        assertTrue(mainPage.checkOrderButton());
        mainPage.clickLkButton();
        profilePage.clickConstructorButton();
        mainPage.checkHeadingConstructBurger();
        mainPage.checkOrderButton();
    }

    @Test
    @DisplayName("Проверка перехода по клику на логотип Stellar Burgers")
    public void checkTransferToLogoSBButton() {
        mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLkButton();
        loginPage.onLoginPage();
        loginPage.setEmail(user.email);
        loginPage.setPassword(user.password);
        loginPage.clickLoginButton();
        assertTrue(mainPage.checkOrderButton());
        mainPage.clickLkButton();
        profilePage.clickLogoSB();
        mainPage.checkHeadingConstructBurger();
        mainPage.checkOrderButton();
    }

    @Test
    @DisplayName("Проверка выхода по кнопке «Выйти» в личном кабинете")
    public void checkExitProfilePage() {
        mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLkButton();
        loginPage.onLoginPage();
        loginPage.setEmail(user.email);
        loginPage.setPassword(user.password);
        loginPage.clickLoginButton();
        assertTrue(mainPage.checkOrderButton());
        mainPage.clickLkButton();
        profilePage.clickExitButton();
        loginPage.onLoginPage();
    }
}
