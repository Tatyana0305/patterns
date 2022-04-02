import com.github.javafaker.Faker;
import entitles.RegistrationInfo;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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

    private void printTestData(RegistrationInfo info){
        System.out.println(info);

    }

    private void printTestDataRepeat(String name, String phone, String cardNumber) {
        System.out.print(StringUtils.repeat("=", 30));
        System.out.print(name + "\n" + phone + "\n" + cardNumber);
    }

}
