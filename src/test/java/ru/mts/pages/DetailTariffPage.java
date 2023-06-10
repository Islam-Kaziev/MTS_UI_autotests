package ru.mts.pages;

import ru.mts.pages.components.ConnectTariffComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class DetailTariffPage {

        public void openPage() {
            open("/mobilnaya-svyaz/tarifi/vse-tarifi/mobile-tv-inet");
        }

        public void inputAddress(String value) {
            $("ymaps.ymaps-2-1-79-inner-panes").shouldBe(visible);
            $("input[placeholder=\"Город, улица, дом\"]").setValue(value);
            $("span.error-notification").shouldBe(hidden);
        }

        public void chooseTariff() {
            $("button.btn").click();
            $("mts-universal-card-button.card-btn").click();
        }

        public void chooseInvalidAddress() {
            $("span.mat-option-text").click();
        }

        public void checkAddress() {
            $("button.btn.btn-loader").shouldBe(visible);
            $("button.btn.btn-loader").doubleClick();
            $("button.btn.btn-loader").click();
        }

        public void clickOnCheckButton() {
            $("span.error-notification").shouldBe(hidden);
            $("input[placeholder=\"Город, улица, дом\"]").shouldBe(enabled);
            $("button.btn_primary").doubleClick();
        }

        public void checkResponseHeader() {
            $("div.title-text").shouldHave(exactText("Заявка на подключение отправлена"));
        }

        public void checkResponseBody() {
            $("div.success-content").shouldHave(text("В ближайшее время наш оператор свяжется"));
        }

    public void checkNegativeResponseHeader() {
        $("h2.result-modal__title").shouldHave(exactText("Подключение этого тарифа по вашему адресу недоступно"));
    }

    public void checkNegativeResponseBody() {
        $("p.result-modal__subtitle").shouldHave(text("Вы можете изменить адрес подключения"));
    }

        public void connectTariffOptions(String name, String phone) {
            $("input#username").setValue(name);
            $("input#phone").setValue(phone);
            $x("//span[contains(text(), 'Роутер не нужен')]").click();
            $("div.tv-devices__item-wrapper--unselected").click();
            $("span.btn-loader__text").doubleClick();
        }

    public void chooseValidAddress() {
        $("span.error-notification").shouldBe(hidden);
        $("span.mat-option-text").shouldBe(visible);
        $("span.mat-option-text").click();
    }

    }

