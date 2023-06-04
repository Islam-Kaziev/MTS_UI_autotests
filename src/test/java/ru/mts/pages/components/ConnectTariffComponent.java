package ru.mts.pages.components;

import static com.codeborne.selenide.Selenide.$;

public class ConnectTariffComponent {
    public void connectAccessibleTariff(String username, String phone) {
        $("div.btn_tariff-card").click();
        $("input#username").setValue(username);
        $("input#phone").setValue(phone);
        $("span.btn-loader__text").doubleClick();
    }

}
