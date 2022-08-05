package ru.yandex.practicum.stellarburgers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.stellarburgers.PageObject.LoginPage;
import ru.yandex.practicum.stellarburgers.PageObject.RegisterPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterPageTest {
    private static RegisterPage registerPage;
    LoginPage loginPage = Selenide.page(LoginPage.class);
    User user;

    @Before
    public void setup() {
        //настройка для запуска тестов в YandexBrowser
        /*System.setProperty("webdriver.chrome.driver", "./src/main/resources/yandexdriver");
        WebDriver driver = new ChromeDriver();
        setWebDriver(driver);*/

        registerPage = open(RegisterPage.URL, RegisterPage.class);
    }

    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Проверка регистрации с паролем некорректной длины")
    public void checkRegisterWithWrongPassword() {
        user = User.getRandomUserWithWrongPassword();
        registerPage.setName(user.name);
        registerPage.setEmail(user.email);
        registerPage.setPassword(user.password);
        registerPage.clickRegisterButton();
        assertEquals("Валидация пароля не пройдена", "Некорректный пароль", registerPage.getErrorMessage());
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    public void checkSuccessRegister() {
        user = User.getRandomCorrectUser();
        registerPage.setName(user.name);
        registerPage.setEmail(user.email);
        registerPage.setPassword(user.password);
        registerPage.clickRegisterButton();
        assertTrue("Переход на страницу входа не выполнен!", loginPage.onLoginPage());
    }
}
