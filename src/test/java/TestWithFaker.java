import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import entitles.RegistrationInfo;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import utils.DataGenerator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class TestWithFaker {
    private static Faker faker;
    RegistrationInfo info = DataGenerator.Registration
            .generateInfo("ru");

    LocalDate localDate = LocalDate.now().plusDays(3);
    DateTimeFormatter data = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String strData = localDate.format(data);


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
        System.out.println(StringUtils.repeat("=", 30));

    }

    private void printTestData(String name, String phone, String city) {

        System.out.print(StringUtils.repeat("=", 30));
        System.out.print(name + "\n" + phone + "\n" + city);
    }

    @Test
    public void shouldOrderCardBySpecificDay() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1908х900";
        $("[data-test-id=city] input").setValue(faker.address().city());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").val(strData);
        $("[data-test-id=name] input").setValue(faker.name().fullName());
        $("[data-test-id=phone] input").setValue(faker.phoneNumber().phoneNumber());
        $("[data-test-id=agreement]").click();

        $x("//*[text()=\"Забронировать\"]").click();
        $("[data-test-id=notification]")
                .shouldHave(Condition.text("Успешно! Встреча успешно забронирована на " + strData),
                        Duration.ofSeconds(15));                        //Загрузка не более 15 секунд
        LocalDate localDate2 = LocalDate.now().plusDays(5);
        DateTimeFormatter data2 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String strData2  = localDate2.format(data2);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").val(strData2);
        $("div .button").click();
        $("[data-test-id='replan-notification'] .notification__content")
                .should(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id='replan-notification'] .button").click();
        $("[data-test-id='success-notification'] .notification__content")
                .should(Condition.text("Встреча успешно запланирована на " + strData2), Duration.ofSeconds(15));

    }
    }
