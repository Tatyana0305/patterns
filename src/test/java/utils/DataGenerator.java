package utils;

import com.github.javafaker.Faker;
import entitles.RegistrationInfo;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class DataGenerator {
    private DataGenerator() {
    }

    static Faker faker = new Faker(new Locale("ru"));

    public static String generatePhone() {
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static String[] validNames = new String[]{
            "Иванов Василий", "Петров Антон", "Смирнова Ольга", "Иванова Ирина"
    };

    public static String generateValidName() {
        Random generator = new Random();
        int randomIndex = generator.nextInt(3);
        return validNames[randomIndex];
    }

    public static String[] names2 = new String[]{
            "Иванов Артём", "Рина Зелёная", "Фёдор Достоевский"};


    public static String generateName2() {
        Random generator = new Random();
        int randomIndex = generator.nextInt(2);
        return names2[randomIndex];
    }


    public static String[] cities = new String[]{
            "Казань", "Ставрополь", "Вологда", "Москва", "Санкт-Петербург", "Краснодар", "Ростов-на-Дону"
    };

    public static String generateCity() {
        Random generator = new Random();
        int randomIndex = generator.nextInt(6);
        return cities[randomIndex];
    }

    public static String[] cities2 = new String[]{
            "Сочи", "Новокузнецк", "Магнитогорск", "Нижневартовск"
    };

    public static String generateCity2() {
        Random generator = new Random();
        int randomIndex = generator.nextInt(3);
        return cities2[randomIndex];
    }


    public static String generateDate(int days) {
        String date = LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }


    public static RegistrationInfo getUser() {
        RegistrationInfo user = new RegistrationInfo(generateValidName(), generatePhone(), generateCity());
        return user;
    }


    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
