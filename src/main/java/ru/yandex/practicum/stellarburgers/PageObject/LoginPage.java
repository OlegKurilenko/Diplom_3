package ru.yandex.practicum.stellarburgers.PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/login";

    //поле email
    @FindBy(how = How.XPATH, using = "//label[text()='Email']/../input")
    private SelenideElement fieldEmail;

    //поле Пароль
    @FindBy(how = How.XPATH, using = "//label[text()='Пароль']/../input")
    private SelenideElement fieldPassword;

    //кнопка Вход
    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement loginButton;

    //Заголовок "Вход"
    @FindBy(how = How.XPATH, using = "//div/h2[text()='Вход']")
    private SelenideElement headingLoginPage;

    public Boolean onLoginPage() {
        headingLoginPage.shouldBe(Condition.visible);
        return true;
    }

    public void setEmail(String email) {
        fieldEmail.setValue(email);
    }

    public void setPassword(String password) {
        fieldPassword.setValue(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

}
