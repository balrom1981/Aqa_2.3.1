package ru.netology.web;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class DataHelper {

    public String setNewDate(int plusDays) {
        return LocalDate.now().plusDays(plusDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public String setNewName(Faker faker) {
        return faker.name().fullName();
    }

    public String setNewPhoneNumber(Faker faker) {
        return faker.phoneNumber().phoneNumber();
    }

    public String setNewCity(Faker faker) {
        return faker.address().city();
    }
}
