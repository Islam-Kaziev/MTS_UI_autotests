package ru.mts.tests;

import java.util.Random;

public class TestData {

    static Random random = new Random();


    public static String validAddressesRandom() {
        String[] addresses = {"Ленинский проспект 147к3", "ленина 14", "ленина 15"};
        int index = random.nextInt(addresses.length);
        return addresses[index];
    }

    public static String invalidAddressesRandom() {
        String[] addresses = {"Тула Проспект Ленина 130", "Воронеж, улица Хользунова, 25/1", "Красноармейская улица, 26"};
        int index = random.nextInt(addresses.length);
        return addresses[index];
    }
}
