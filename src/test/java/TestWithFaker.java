import com.github.javafaker.Faker;
import entitles.RegistrationInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import utils.DataGenerator;

import java.util.Locale;

public class TestWithFaker {
    private static Faker faker;

    @BeforeAll
    static void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }


    @Test
    void shouldGenerateTestDataUsingUtils() {
        RegistrationInfo info = DataGenerator.Registration
                .generateInfo("ru");
        printTestData(info);
    }

    private void printTestData(String name, String phone, String cardNumber) {
        System.out.println(StringUtils.repeat("=", 30));
        System.out.println(name + "\n" + phone + "\n" + cardNumber);
    }
}
