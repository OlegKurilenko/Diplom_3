package ru.yandex.practicum.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/account/profile";

    //кнопка Вход
    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement exitButton;

    //поле Имя
    @FindBy(how = How.XPATH, using = "//label[text()='Имя']/../input")
    private SelenideElement fieldName;

    //поле email
    @FindBy(how = How.XPATH, using = "//label[text()='Логин']/../input")
    private SelenideElement fieldEmail;

    //кнопка Конструктор
    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    private SelenideElement constructorButton;

    //логотип Stellar Burgers
    @FindBy(how = How.XPATH, using = "//div/a")
    private SelenideElement logoSB;


    public void clickExitButton() {
        exitButton.click();
    }

    public void clickConstructorButton() {
        constructorButton.click();
    }

    public void clickLogoSB() {
        logoSB.click();
    }

    public String getNameField() {
        return fieldName.getValue();
    }

    public String getEmailField() {
        return fieldEmail.getValue();
    }
}
