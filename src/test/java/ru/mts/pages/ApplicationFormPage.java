package ru.mts.pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ApplicationFormPage {

    public void connectTariffOptionsWithoutClick(String name, String phone) {
        $("input#username").setValue(name);
        $("input#phone").setValue(phone);
        $x("//span[contains(text(), 'Роутер не нужен')]").click();
        $("div.tv-devices__item-wrapper--unselected").click();
        $("span.btn-loader__text").shouldBe(enabled);
        $("span.btn-loader__text").doubleClick();
    }

    public void checkResponseHeader() {
        $("div.title-text").shouldHave(exactText("Заявка на подключение отправлена"));
    }

    public void checkResponseBody() {
        $("div.success-content").shouldHave(text("В ближайшее время наш оператор свяжется"));
    }
}
