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

public class ServicePackagesTests extends TestBase {

    String checkValidAddress = validAddressesRandom();
    String checkInvalidAddress = invalidAddressesRandom();
    String name = "Тестовая заявка";
    private final String PHONE = "9111111111";

    @Test
    @Owner("Казиев Ислам")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка валидного адреса через ПТВ")
    void checkValidPtvAddresses() {

        step("Открываем страничку с Домашними тарифами", () -> {
            servicePackagesPage.openPage();
        });
        step("Вводим валидный адрес для проверки возможности подключения", () -> {
            servicePackagesPage.inputAddress(checkValidAddress);
        });
        step("Выбираем из выпадающего списка нужный адрес - checkAddress", () -> {
            servicePackagesPage.chooseValidAddress();
        });
        step("Кликаем на кнопку Проверить", () -> {
            servicePackagesPage.checkAddress();
        });
        step("Проверка текста с результатом успешной проверки адреса", () -> {
            servicePackagesPage.checkHeaderSuccessText();
        });
        step("Проверка наличия кнопки Проверить другой адрес", () -> {
            servicePackagesPage.checkButtonVisible();
        });
    }

    @Test
    @Owner("Казиев Ислам")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка валидного адреса указав место на карте")
    void checkValidAddressesUsingMap() {

        step("Открываем страничку с Домашними тарифами", () -> {
            servicePackagesPage.openPage();
        });
        step("Открываем карту кликая на Указать адрес на карте", () -> {
            servicePackagesPage.openMap();
        });
        step("Вводим валидный адрес для проверки возможности подключения", () -> {
            servicePackagesPage.inputAddressOnMap(checkValidAddress);
        });
        step("Выбираем из выпадающего списка нужный адрес - checkAddress", () -> {
            servicePackagesPage.chooseValidAddress();
        });
        step("Кликаем на кнопку Проверить", () -> {
            servicePackagesPage.clickOnCheckButton();
        });
        step("Проверка текста с результатом успешной проверки адреса", () -> {
            servicePackagesPage.checkSuccessText();
        });
        step("Проверка наличия кнопки Проверить другой адрес", () -> {
            servicePackagesPage.checkButtonVisible();
        });
    }

    @Test
    @Owner("Казиев Ислам")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка успешной отправки заявки на подключение тарифа после успешного прохождения ПТВ")
    void sendingConnectionRequest() {

        step("Открываем страничку с Домашними тарифами", () -> {
            servicePackagesPage.openPage();
        });
        step("Вводим валидный адрес для проверки возможности подключения", () -> {
            servicePackagesPage.inputAddress(checkValidAddress);
        });
        step("Выбираем из выпадающего списка нужный адрес - checkAddress", () -> {
            servicePackagesPage.chooseValidAddress();
        });
        step("Кликаем на кнопку Проверить", () -> {
            servicePackagesPage.checkAddress();
        });
        step("Проверка текста с результатом успешной проверки адреса", () -> {
            servicePackagesPage.checkHeaderSuccessText();
        });
        step("Проверка наличия кнопки Проверить другой адрес", () -> {
            servicePackagesPage.checkButtonVisible();
        });
        step("Подключение одного из доступных тарифов", () -> {
            servicePackagesPage.connectAvailableTariff(name, PHONE);
        });
        step("Проверка заголовка ответа об успешной отправке заявки", () -> {
            servicePackagesPage.checkResponseHeader();
        });
        step("Проверка тела ответа об успешной отправке заявки", () -> {
            servicePackagesPage.checkResponseBody();
        });
    }

    @Test
    @Owner("Казиев Ислам")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Изменения состава продуктов при отправке заявки")
    void changeProductsConnectionRequest() {

        step("Открываем страничку с Домашними тарифами", () -> {
            servicePackagesPage.openPage();
        });
        step("Вводим валидный адрес для проверки возможности подключения", () -> {
            servicePackagesPage.inputAddress(checkValidAddress);
        });
        step("Выбираем из выпадающего списка нужный адрес - checkAddress", () -> {
            servicePackagesPage.chooseValidAddress();
        });
        step("Кликаем на кнопку Проверить", () -> {
            servicePackagesPage.checkAddress();
        });
        step("Проверка текста с результатом успешной проверки адреса", () -> {
            servicePackagesPage.checkHeaderSuccessText();
        });
        step("Проверка наличия кнопки Проверить другой адрес", () -> {
            servicePackagesPage.checkButtonVisible();
        });
        step("Подключеняем тариф без роутера и с покупкой ТВ-оборудования", () -> {
            servicePackagesPage.connectTariffWithOptions(name, PHONE);
        });
        step("Проверка заголовка ответа об успешной отправке заявки", () -> {
            servicePackagesPage.checkResponseHeader();
        });
        step("Проверка тела ответа об успешной отправке заявки", () -> {
            servicePackagesPage.checkResponseBody();
        });
    }

    @Test
    @Owner("Казиев Ислам")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("negative")
    @DisplayName("Проверка невалидного адреса на котором нет возможности подключения")
    void checkInvalidPtvAddresses() {

        step("Открываем страничку с Домашними тарифами", () -> {
            servicePackagesPage.openPage();
        });
        step("Вводим невалидный адрес для проверки возможности подключения", () -> {
            servicePackagesPage.inputAddress(checkInvalidAddress);
        });
        step("Выбираем из выпадающего списка нужный адрес - checkAddress", () -> {
            servicePackagesPage.chooseInvalidAddress();
        });
        step("Кликаем на кнопку Проверить", () -> {
            servicePackagesPage.checkAddress();
        });
        step("Проверка текста с результатом проверки невалидного адреса", () -> {
            servicePackagesPage.checkFailureText();
        });
        step("Проверка наличия кнопки Изменить адрес", () -> {
            servicePackagesPage.checkInvalidButtonVisible();
        });
    }
}
