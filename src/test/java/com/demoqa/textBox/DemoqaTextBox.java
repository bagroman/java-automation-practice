package com.demoqa.textBox;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class DemoqaTextBox {
    String userName = "[id='userName']";
    String userEmail = "[id='userEmail']";
    String currentAddress = "[id='currentAddress']";
    String permanentAddress = "[id='permanentAddress']";
    String submitButton = "[id='submit']";
    String result = "[id='output']";

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    public void submitForm() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем форму", () -> {
            open("/text-box");
            Allure.getLifecycle().addAttachment(
                    "Исходники страницы",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });
        step("Заполняем поля", () -> {
            $(userName).setValue("Igor");
            $(userEmail).setValue("test@test.ru");
            $(currentAddress).setValue("ivanovo");
            $(permanentAddress).setValue("perm");
        });
        step("Нажимаем на кнопку и проверяем отображение результата", () -> {
            $(submitButton).click();
            $(result).shouldBe(visible);
            Allure.getLifecycle().addAttachment(
                    "Исходники страницы",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });
    }
}
