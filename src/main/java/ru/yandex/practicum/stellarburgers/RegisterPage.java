package ru.yandex.practicum.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/register";

    //поле Имя
    @FindBy(how = How.XPATH, using = "//label[text()='Имя']/../input")
    private SelenideElement fieldName;

    //поле email
    @FindBy(how = How.XPATH, using = "//label[text()='Email']/../input")
    private SelenideElement fieldEmail;

    //поле password
    @FindBy(how = How.XPATH, using = "//label[text()='Пароль']/../input")
    private SelenideElement fieldPassword;

    //кнопка Зарегистрироваться
    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    //кнопка Войти
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement enterButton;

    //Сообщение "Некорректный пароль"
    @FindBy(how = How.XPATH, using = "//div/p[@class='input__error text_type_main-default']")
    private SelenideElement errorMessage;

    public void setName(String name) {
        fieldName.setValue(name);
    }

    public void setEmail(String email) {
        fieldEmail.setValue(email);
    }

    public void setPassword(String password) {
        fieldPassword.setValue(password);
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    public void clickEnterButton() {
        enterButton.click();
    }
}
