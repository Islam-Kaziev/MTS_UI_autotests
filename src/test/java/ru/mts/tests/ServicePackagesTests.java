package ru.mts.tests;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static ru.mts.tests.TestData.*;

public class ServicePackagesTests extends TestBase {
    String checkAddress = "Ленинский проспект 147к3";
    String name = "Тестовая заявка";
    String phone = "9111111111";

    @Test
    @DisplayName("Проверка валидного адреса через ПТВ")
    void checkValidPtvAddresses() {

        step("Открываем страничку с Домашними тарифами", () -> {
            open("/dom/home-allmts/spb-city");
        });
        step("Вводим валидный адрес для проверки возможности подключения", () -> {
            $("input[placeholder=\"Город, улица, дом\"]").setValue(checkAddress);
        });
        step("Выбираем из выпадающего списка нужный адрес - checkAddress", () -> {
            $x("//b[contains(text(), 'Ленинский проспект, 147к3')]").click();
        });
        step("Кликаем на кнопку Проверить", () -> {
            $("button.btn.btn-loader").doubleClick();
        });
        step("Проверка текста с результатом успешной проверки адреса", () -> {
            $x("//p[contains(text(), 'доступен для подключения')]").click();
        });
        step("Проверка наличия кнопки Проверить другой адрес", () -> {
            $("div.btn_secondary").shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Проверка валидного адреса указав место на карте")
    void checkValidAddressesUsingMap() {

        step("Открываем страничку с Домашними тарифами", () -> {
            open("/dom/home-allmts/spb-city");
        });
        step("Открываем карту кликая на Указать адрес на карте", () -> {
            $x("//span[contains(text(), 'Указать')]").click();
        });
        step("Вводим валидный адрес для проверки возможности подключения", () -> {
            $("input[placeholder=\"Город, улица, дом\"]", 1).setValue(checkAddress);
        });
        step("Выбираем из выпадающего списка нужный адрес - checkAddress", () -> {
            $x("//b[contains(text(), 'Ленинский проспект, 147к3')]").click();
        });
        step("Кликаем на кнопку Проверить", () -> {
            $("button.btn_primary").doubleClick();
        });
        step("Проверка текста с результатом успешной проверки адреса", () -> {
            $x("//p[contains(text(), 'доступен для подключения')]").click();
        });
        step("Проверка наличия кнопки Проверить другой адрес", () -> {
            $("div.btn_secondary").shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Проверка успешной отправки заявки на подключение тарифа после успешного прохождения ПТВ")
    void sendingConnectionRequest() {

        step("Открываем страничку с Домашними тарифами", () -> {
            open("/dom/home-allmts/spb-city");
        });
        step("Вводим валидный адрес для проверки возможности подключения", () -> {
            $("input[placeholder=\"Город, улица, дом\"]").setValue(checkAddress);
        });
        step("Выбираем из выпадающего списка нужный адрес - checkAddress", () -> {
            $x("//b[contains(text(), 'Ленинский проспект, 147к3')]").click();
        });
        step("Кликаем на кнопку Проверить", () -> {
            $("button.btn.btn-loader").doubleClick();
        });
        step("Проверка текста с результатом успешной проверки адреса", () -> {
            $x("//p[contains(text(), 'доступен для подключения')]").click();
        });
        step("Проверка наличия кнопки Проверить другой адрес", () -> {
            $("div.btn_secondary").shouldBe(visible);
        });
        step("Подключение одного из доступных тарифов", () -> {
            $("div.btn_tariff-card").click();
        });
        step("Ввод имени", () -> {
            $("input#username").setValue(name);
        });
        step("Ввод телефона", () -> {
            $("input#phone").setValue(phone);
        });
        step("Нажатие кнопки Отправить заявку", () -> {
            $("span.btn-loader__text").doubleClick();
        });
        step("Проверка заголовка ответа об успешной отправке заявки", () -> {
            $("div.title-text").shouldHave(exactText("Заявка на подключение отправлена"));
        });
        step("Проверка тела ответа об успешной отправке заявки", () -> {
            $("div.success-content").shouldHave(text("В ближайшее время наш оператор свяжется"));;
        });
    }
}
