package ru.netology.web;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

class DeliveryNewTest {
    private Faker faker;
    DataHelper dataHelper = new DataHelper();

        @BeforeEach
    void shouldOpenWeb() {
        open("http://localhost:9999");
        faker = new Faker(new Locale("ru"));


    }

    @Test
    void shouldAcceptInformation() {
        $("[data-test-id=city] input").setValue(dataHelper.setNewCity(faker));
        $("[data-test-id='date'] input")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dataHelper.setNewDate(3));
        $("[data-test-id='name'] input").setValue(dataHelper.setNewName(faker));
        $("[data-test-id='phone'] input").setValue(dataHelper.setNewPhoneNumber(faker));
        $("[data-test-id=agreement]").click();
        $$("button").get(1).click();
        $(withText("Встреча успешно"))
                .shouldBe(visible, Duration.ofSeconds(15));
        $(withText(dataHelper.setNewDate(3))).shouldBe(visible);
        $("[data-test-id='date'] input")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dataHelper.setNewDate(5));
        $$("button").get(1).click();
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?"))
                .shouldBe(visible, Duration.ofSeconds(15));
        $(withText("Перепланировать")).click();
        $(withText("Встреча успешно ")).shouldBe(visible);
    }

}

