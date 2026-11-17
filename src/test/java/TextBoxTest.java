import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTest extends TestBase {

    @Test
    void successfulFullFieldsTest() {
        open("/text-box.html");

        $("#userName").setValue("Auto Test");
        $("#userEmail").setValue("autotest@gmail.com");
        $("#currentAddress").setValue("Первая строка\nВторая строка (д. № 9'A')");
        $("#permanentAddress").setValue("Первая строка\nВторая строка (д. № 9'A')");
        $("#submit").click();

        $("#output").shouldBe(visible).shouldHave(
                text("Name:Auto Test"),
                text("Email:autotest@gmail.com"),
                text("Current Address :Первая строка Вторая строка (д. № 9'A')"),
                text("Permananet Address :Первая строка Вторая строка (д. № 9'A')"));
    }

    @Test
    void checkEmptyFieldsTest() {
        open("/text-box.html");
        $("#submit").click();

        $("#output").shouldBe(visible).shouldHave(
                text("Name:"),
                text("Email:"),
                text("Current Address :"),
                text("Permananet Address :"));
    }
}
