package utils;

import com.github.javafaker.Faker;
import entitles.RegistrationInfo;
import lombok.experimental.UtilityClass;

import java.util.Locale;


@UtilityClass

public class DataGenerator {

    @UtilityClass
    public static class Registration {
        public static RegistrationInfo generateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationInfo(faker.name().fullName(),
                    faker.phoneNumber().phoneNumber(),
                    faker.finance().creditCard());

        }

    }
}
