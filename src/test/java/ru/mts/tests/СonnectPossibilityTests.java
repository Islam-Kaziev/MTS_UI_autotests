package ru.mts.tests;


import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static io.qameta.allure.Allure.step;
import static ru.mts.tests.TestData.invalidAddressesRandom;
import static ru.mts.tests.TestData.validAddressesRandom;

@Owner("Казиев Ислам")
@Severity(SeverityLevel.CRITICAL)
public class СonnectPossibilityTests extends TestBase {

    String checkValidAddress = validAddressesRandom();
    String checkInvalidAddress = invalidAddressesRandom();
    String name = "Тестовая заявка";
    private final String PHONE = "9111111111";

    @Test
    @DisplayName("Проверка валидного адреса через ПТВ")
    void checkValidPtvAddresses() {

        step("Открываем страничку с Домашними тарифами", () -> {
            connectPossibilityPage.openPage();
        });
        step("Вводим валидный адрес для проверки возможности подключения", () -> {
            connectPossibilityPage.inputAddress(checkValidAddress);
        });
        step("Выбираем из выпадающего списка нужный адрес - checkAddress", () -> {
            connectPossibilityPage.chooseValidAddress();
        });
        step("Кликаем на кнопку Проверить", () -> {
            connectPossibilityPage.checkAddress();
        });
        step("Проверка текста с результатом успешной проверки адреса", () -> {
            connectPossibilityPage.checkHeaderSuccessText();
        });
        step("Проверка наличия кнопки Проверить другой адрес", () -> {
            connectPossibilityPage.checkButtonVisible();
        });
    }

    @Test
    @DisplayName("Проверка валидного адреса указав место на карте")
    void checkValidAddressesUsingMap() {

        step("Открываем страничку с Домашними тарифами", () -> {
            connectPossibilityPage.openPage();
        });
        step("Открываем карту кликая на Указать адрес на карте", () -> {
            connectPossibilityPage.openMap();
        });
        step("Вводим валидный адрес для проверки возможности подключения", () -> {
            connectPossibilityPage.inputAddressOnMap(checkValidAddress);
        });
        step("Выбираем из выпадающего списка нужный адрес - checkAddress", () -> {
            connectPossibilityPage.chooseValidAddress();
        });
        step("Кликаем на кнопку Проверить", () -> {
            connectPossibilityPage.clickOnCheckButton();
        });
        step("Проверка текста с результатом успешной проверки адреса", () -> {
            connectPossibilityPage.checkSuccessText();
        });
        step("Проверка наличия кнопки Проверить другой адрес", () -> {
            connectPossibilityPage.checkButtonVisible();
        });
    }

    @Test
    @DisplayName("Проверка успешной отправки заявки на подключение тарифа после успешного прохождения ПТВ")
    void sendingConnectionRequest() {

        step("Открываем страничку с Домашними тарифами", () -> {
            connectPossibilityPage.openPage();
        });
        step("Вводим валидный адрес для проверки возможности подключения", () -> {
            connectPossibilityPage.inputAddress(checkValidAddress);
        });
        step("Выбираем из выпадающего списка нужный адрес - checkAddress", () -> {
            connectPossibilityPage.chooseValidAddress();
        });
        step("Кликаем на кнопку Проверить", () -> {
            connectPossibilityPage.checkAddress();
        });
        step("Проверка текста с результатом успешной проверки адреса", () -> {
            connectPossibilityPage.checkHeaderSuccessText();
        });
        step("Проверка наличия кнопки Проверить другой адрес", () -> {
            connectPossibilityPage.checkButtonVisible();
        });
        step("Подключение одного из доступных тарифов", () -> {
            connectPossibilityPage.connectTariffOptions(name, PHONE);
        });
        step("Проверка заголовка ответа об успешной отправке заявки", () -> {
            applicationFormPage.checkResponseHeader();
        });
        step("Проверка тела ответа об успешной отправке заявки", () -> {
            applicationFormPage.checkResponseBody();
        });
    }

    @Test
    @DisplayName("Изменения состава продуктов при отправке заявки")
    void changeProductsConnectionRequest() {

        step("Открываем страничку с Домашними тарифами", () -> {
            connectPossibilityPage.openPage();
        });
        step("Вводим валидный адрес для проверки возможности подключения", () -> {
            connectPossibilityPage.inputAddress(checkValidAddress);
        });
        step("Выбираем из выпадающего списка нужный адрес - checkAddress", () -> {
            connectPossibilityPage.chooseValidAddress();
        });
        step("Кликаем на кнопку Проверить", () -> {
            connectPossibilityPage.checkAddress();
        });
        step("Проверка текста с результатом успешной проверки адреса", () -> {
            connectPossibilityPage.checkHeaderSuccessText();
        });
        step("Проверка наличия кнопки Проверить другой адрес", () -> {
            connectPossibilityPage.checkButtonVisible();
        });
        step("Подключеняем тариф без роутера и с покупкой ТВ-оборудования", () -> {
            connectPossibilityPage.connectTariffWithOptions(name, PHONE);
        });
        step("Проверка заголовка ответа об успешной отправке заявки", () -> {
            applicationFormPage .checkResponseHeader();
        });
        step("Проверка тела ответа об успешной отправке заявки", () -> {
            applicationFormPage.checkResponseBody();
        });
    }

    @Test
    @Tag("negative")
    @DisplayName("Проверка невалидного адреса на котором нет возможности подключения")
    void checkInvalidPtvAddresses() {

        step("Открываем страничку с Домашними тарифами", () -> {
            connectPossibilityPage.openPage();
        });
        step("Вводим невалидный адрес для проверки возможности подключения", () -> {
            connectPossibilityPage.inputAddress(checkInvalidAddress);
        });
        step("Выбираем из выпадающего списка нужный адрес - checkAddress", () -> {
            connectPossibilityPage.chooseInvalidAddress();
        });
        step("Кликаем на кнопку Проверить", () -> {
            connectPossibilityPage.checkAddress();
        });
        step("Проверка текста с результатом проверки невалидного адреса", () -> {
            connectPossibilityPage.checkFailureText();
        });
        step("Проверка наличия кнопки Изменить адрес", () -> {
            connectPossibilityPage.checkInvalidButtonVisible();
        });
    }
}
