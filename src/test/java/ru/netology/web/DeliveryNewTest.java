package ru.netology.web;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;


class DeliveryNewTest {


    @BeforeEach
    void shouldOpenWeb() {
        open("http://localhost:9999");



    }

    @Test
    void shouldAcceptInformation() {
        $("[data-test-id=city] input").setValue(DataHelper.getNewCity());
        $("[data-test-id='date'] input")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(DataHelper.getNewDate(3));
        $("[data-test-id='name'] input").setValue(DataHelper.getNewName());
        $("[data-test-id='phone'] input").setValue(DataHelper.getNewPhoneNumber());
        $("[data-test-id=agreement]").click();
        $$("button").get(1).click();
        String dateFirst = $("[data-test-id='date']").getCssValue("value");
        $(withText("Встреча успешно" + dateFirst))
                .shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='date'] input")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(DataHelper.getNewDate(5));
        $$("button").get(1).click();
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?"))
                .shouldBe(visible, Duration.ofSeconds(15));
        $(withText("Перепланировать")).click();
        String dateSecond = $("[data-test-id='date']").getCssValue("value");
        $(withText("Встреча успешно " + dateSecond)).shouldBe(visible);
    }

}

