package ru.mts.pages;

import ru.mts.pages.components.ConnectTariffComponent;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ServicePackagesPage {

    ConnectTariffComponent connectTariffComponent = new ConnectTariffComponent();


    public void openPage() {
        open("/dom/home-allmts/spb-city");
    }

    public void inputAddress(String value) {
        $("input[placeholder=\"Город, улица, дом\"]").setValue(value);
    }

    public void chooseValidAddress() {
        $("span.mat-option-text").click();
    }

    public void chooseInvalidAddress() {
        $x("//b[contains(text(), 'проспект Ленина, 130')]").click();
    }

    public void checkAddress() {
        $("button.btn.btn-loader").shouldBe(visible);
        $("button.btn.btn-loader").doubleClick();
        $("button.btn.btn-loader").click();
    }

    public void checkHeaderSuccessText() {
        $x("//p[contains(text(), 'доступен для подключения')]").click();
    }

    public void checkButtonVisible() {
        $("div.btn_secondary").shouldBe(visible);
    }

    public void checkInvalidButtonVisible() {
        $("button.btn_secondary").shouldBe(visible);
    }

    public void openMap() {
        $x("//span[contains(text(), 'Указать')]").click();
    }

    public void inputAddressOnMap(String value) {
        $("input[placeholder=\"Город, улица, дом\"]", 1).setValue(value);
    }

    public void clickOnCheckButton() {
        $("span.error-notification").shouldBe(hidden);
        $("button.btn_primary").doubleClick();
    }

    public void checkSuccessText() {
        $x("//p[contains(text(), 'доступен для подключения')]").click();
    }


    public void connectAvailableTariff(String username, String phone) {
        connectTariffComponent.connectAccessibleTariff(username, phone);
    }

    public void checkResponseHeader() {
        $("div.title-text").shouldHave(exactText("Заявка на подключение отправлена"));
    }

    public void checkResponseBody() {
        $("div.success-content").shouldHave(text("В ближайшее время наш оператор свяжется"));
    }

    public void connectTariffWithOptions(String name, String phone) {
        $("div.btn_tariff-card").click();
        $("input#username").setValue(name);
        $("input#phone").setValue(phone);
        $x("//span[contains(text(), 'Роутер не нужен')]").click();
        $("div.tv-devices__item-wrapper--unselected").click();
        $("span.btn-loader__text").shouldBe(enabled);
        $("span.btn-loader__text").doubleClick();
    }

    public void checkFailureText() {
        $("p.connect-address-form-result__address").shouldHave(text("Пока не можем подключить услуги"));
    }

}
