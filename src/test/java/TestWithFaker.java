import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import utils.DataGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestWithFaker {

    @BeforeEach
    void openBrowser() {
        open("http://localhost:9999");
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1908х900";

    }

    @Test
    public void shouldOrderCardBySpecificDay() {

        $("[data-test-id=city] input").setValue(DataGenerator.generateCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").val(DataGenerator.generateDate(3));
        $("[data-test-id=name] input").setValue(DataGenerator.generateValidName());
        $("[data-test-id=phone] input").setValue(DataGenerator.generatePhone());
        $("[data-test-id=agreement]").click();

        $x("//*[text()=\"Запланировать\"]").click();
        $("[data-test-id=success-notification]").should(visible);

        LocalDate localDate2 = LocalDate.now().plusDays(5);
        DateTimeFormatter data2 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String strData2 = localDate2.format(data2);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").val(strData2);
        $("div .button").click();
        $("[data-test-id='replan-notification'] .notification__content")
                .shouldBe(visible).shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));

        $(".notification_status_error .button").click();

        $(withText("Успешно!")).shouldBe(visible);
        $("[data-test-id='success-notification'] .notification__content").shouldBe(visible).shouldHave
                (exactText("Встреча успешно запланирована на " + strData2));

    }

    @Test
    public void shouldOrderCardForNotAvailableAddress() {

        $("[data-test-id=city] input").setValue(DataGenerator.generateCity2());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").val(DataGenerator.generateDate(3));
        $("[data-test-id=name] input").setValue(DataGenerator.generateValidName());
        $("[data-test-id=phone] input").setValue(DataGenerator.generatePhone());
        $("[data-test-id=agreement]").click();

        $x("//*[text()=\"Запланировать\"]").click();
        $("[data-test-id=success-notification]").should(visible);

        LocalDate localDate2 = LocalDate.now().plusDays(5);
        DateTimeFormatter data2 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String strData2 = localDate2.format(data2);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").val(strData2);
        $("div .button").click();
        $("[data-test-id='replan-notification'] .notification__content")
                .shouldBe(visible).shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));

        $(".notification_status_error .button").click();

        $(withText("Успешно!")).shouldBe(visible);
        $("[data-test-id='success-notification'] .notification__content").shouldBe(visible).shouldHave
                (exactText("Встреча успешно запланирована на " + strData2));

    }

    @Test
    public void shouldOrderCardIfNameWidthInvalidCharacter() {

        $("[data-test-id=city] input").setValue(DataGenerator.generateCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").val(DataGenerator.generateDate(3));
        $("[data-test-id=name] input").setValue(DataGenerator.generateName2());
        $("[data-test-id=phone] input").setValue(DataGenerator.generatePhone());
        $("[data-test-id=agreement]").click();

        $x("//*[text()=\"Запланировать\"]").click();
        $("[data-test-id=success-notification]").should(visible);

        LocalDate localDate2 = LocalDate.now().plusDays(5);
        DateTimeFormatter data2 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String strData2 = localDate2.format(data2);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").val(strData2);
        $("div .button").click();
        $("[data-test-id='replan-notification'] .notification__content")
                .shouldBe(visible).shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));

        $(".notification_status_error .button").click();

        $(withText("Успешно!")).shouldBe(visible);
        $("[data-test-id='success-notification'] .notification__content").shouldBe(visible).shouldHave
                (exactText("Встреча успешно запланирована на " + strData2));
    }
}