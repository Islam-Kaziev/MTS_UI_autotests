package ru.mts.tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static ru.mts.tests.TestData.invalidAddressesRandom;
import static ru.mts.tests.TestData.validAddressesRandom;

public class DetailTariffTests extends TestBase{

        String checkValidAddress = validAddressesRandom();
        String checkInvalidAddress = invalidAddressesRandom();
        String name = "Тестовая заявка";
        private final String PHONE = "9111111111";

        @Test
        @Owner("Казиев Ислам")
        @Severity(SeverityLevel.CRITICAL)
        @DisplayName("Успешное подключение тарифа МЫ МТС+ из раздела Тарифы и подписки")
        void checkValidPtvAddresses() {

            step("Открываем страничку Тарифы и подписки", () -> {
                detailTariffPage.openPage();
            });
            step("Выбираем тариф МЫ МТС+ нажимая на кнопку Подключить", () -> {
                detailTariffPage.chooseTariff();
            });
            step("Вбиваем валидный адрес", () -> {
                detailTariffPage.inputAddress(checkValidAddress);
            });
            step("Выбираем из выпадающего списка совпадающий адрес", () -> {
                detailTariffPage.chooseValidAddress();
            });
            step("Кликаем на кнопку Проверить", () -> {
                detailTariffPage.clickOnCheckButton();
            });
            step("Подключение одного из доступных тарифов", () -> {
                detailTariffPage.connectTariffOptions(name, PHONE);
            });
            step("Проверка заголовка ответа об успешной отправке заявки", () -> {
                detailTariffPage.checkResponseHeader();
            });
            step("Проверка тела ответа об успешной отправке заявки", () -> {
                detailTariffPage.checkResponseBody();
            });
        }

    @Test
    @Owner("Казиев Ислам")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Неуспешное подключение тарифа МЫ МТС+ из раздела Тарифы и подписки")
    void checkInvalidPtvAddresses() {

        step("Открываем страничку Тарифы и подписки", () -> {
            detailTariffPage.openPage();
        });
        step("Выбираем тариф МЫ МТС+ нажимая на кнопку Подключить", () -> {
            detailTariffPage.chooseTariff();
        });
        step("Вбиваем невалидный адрес", () -> {
            detailTariffPage.inputAddress(checkInvalidAddress);
        });
        step("Выбираем из выпадающего списка совпадающий адрес", () -> {
            detailTariffPage.chooseInvalidAddress();
        });
        step("Кликаем на кнопку Проверить", () -> {
            detailTariffPage.clickOnCheckButton();
        });
        step("Проверка заголовка ответа", () -> {
            detailTariffPage.checkNegativeResponseHeader();
        });
        step("Проверка тела ответа", () -> {
            detailTariffPage.checkNegativeResponseBody();
        });
    }
}



