package ru.yandex.practicum.stellarburgers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static org.junit.Assert.assertTrue;

public class LoginPageTest {
    LoginPage loginPage = Selenide.page(LoginPage.class);
    RegisterPage registerPage = Selenide.page(RegisterPage.class);
    ProfilePage profilePage = Selenide.page(ProfilePage.class);
    MainPage mainPage = Selenide.page(MainPage.class);
    RestorePasswordPage restorePasswordPage = Selenide.page(RestorePasswordPage.class);
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
    @DisplayName("Проверка входа с главной страницы через кнопку \"Личый кабинет\"")
    public void checkLoginMainPageLKButton() {
        mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLkButton();
        loginPage.onLoginPage();
        loginPage.setEmail(user.email);
        loginPage.setPassword(user.password);
        loginPage.clickLoginButton();
        assertTrue(mainPage.checkOrderButton());
        mainPage.clickLkButton();
        profilePage.clickExitButton();
    }

    @Test
    @DisplayName("Проверка входа с главной страницы через кнопку \"Вход в аккаунт\"")
    public void checkLoginMainPageLoginButton() {
        mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();
        loginPage.onLoginPage();
        loginPage.setEmail(user.email);
        loginPage.setPassword(user.password);
        loginPage.clickLoginButton();
        assertTrue(mainPage.checkOrderButton());
        mainPage.clickLkButton();
        profilePage.clickExitButton();
    }

    @Test
    @DisplayName("Проверка входа со страницы регистрации")
    public void checkLoginRegisterPageEnterButton() {
        registerPage = open(RegisterPage.URL, RegisterPage.class);
        registerPage.clickEnterButton();
        loginPage.onLoginPage();
        loginPage.setEmail(user.email);
        loginPage.setPassword(user.password);
        loginPage.clickLoginButton();
        assertTrue(mainPage.checkOrderButton());
        mainPage.clickLkButton();
        profilePage.clickExitButton();
    }

    @Test
    @DisplayName("Проверка входа со страницы восстановления пароля")
    public void checkLoginRestorePaswordPage() {
        restorePasswordPage = open(RestorePasswordPage.URL, RestorePasswordPage.class);
        restorePasswordPage.clickEnterButton();
        loginPage.onLoginPage();
        loginPage.setEmail(user.email);
        loginPage.setPassword(user.password);
        loginPage.clickLoginButton();
        assertTrue(mainPage.checkOrderButton());
        mainPage.clickLkButton();
        profilePage.clickExitButton();
    }
}
