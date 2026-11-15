import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest extends TestBase {

    @Test
    void successfulRegistrationAllFieldsTest() {
        open("/automation-practice-form");
        $("#fixedban button[aria-label='Close']").shouldBe(visible).click();

        $("input[id=firstName]").setValue("Auto");
        $("input[id=lastName]").setValue("Test");
        $("input[id=userEmail]").setValue("autotest@gmail.com");
        $("#genterWrapper").$(byText("Female")).click();
        $("input[id=userNumber]").setValue("9001431430");

        $("input[id=dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOptionContainingText("2000");
        $(".react-datepicker__month-select").selectOptionContainingText("July");
        $(byXpath("//div[@id='datepickerDays']/span[text()='11']")).click();

        $("#subjectsWrapper").click();
        $$("#subjectsDropdown div").findBy(text("Arts")).scrollTo().click();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("input[type='file']").uploadFromClasspath("foto.jpg");

        $("textarea[id='currentAddress']").setValue("Первая строка\nВторая строка\nТретья строка\nСтрока №4");
        $("#state").click();
        $$("#stateCity-wrapper").findBy(text("Haryana")).click();
        $("#city").click();
        $$("#stateCity-wrapper").findBy(text("Karnal")).click();

        $("button[id='submit']").click();

        $("#example-modal-sizes-title-lg").shouldBe(visible).shouldHave(exactText("Thanks for submitting the form"));
        $("#resultBody").$(byText("Student Name")).sibling(0).shouldHave(text("Auto Test"));
        $("#resultBody").$(byText("Student Email")).sibling(0).shouldHave(text("autotest@gmail.com"));
        $("#resultBody").$(byText("Gender")).sibling(0).shouldHave(text("Female"));
        $("#resultBody").$(byText("Mobile")).sibling(0).shouldHave(text("9001431430"));
        $("#resultBody").$(byText("Date of Birth")).sibling(0).shouldHave(text("11 Jul 2000"));
        $("#resultBody").$(byText("Subjects")).sibling(0).shouldHave(text("Arts"));
        $("#resultBody").$(byText("Hobbies")).sibling(0).shouldHave(text("Sports, Music"));
        $("#resultBody").$(byText("Picture")).sibling(0).shouldHave(text("foto.jpg"));
        $("#resultBody").$(byText("Address")).sibling(0).shouldHave(text("Первая строка Вторая строка Третья строка Строка №4"));
        $("#resultBody").$(byText("State and City")).sibling(0).shouldHave(text("Haryana Panipat"));
    }

    @Test
    void successfulRegistrationRequiredFieldsTest() {
        open("/automation-practice-form");
        $("p").shouldHave(exactText("Student Registration Form"));
        $("#fixedban button[aria-label='Close']").shouldBe(visible).click();

        $("input[id=firstName]").setValue("Auto");
        $("input[id=lastName]").setValue("Test");
        $("#genterWrapper").$(byText("Male")).click();
        $("input[id=userNumber]").setValue("9001431430");
        $("button[id='submit']").scrollTo().click();

        $("#example-modal-sizes-title-lg").shouldBe(visible).shouldHave(exactText("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Auto Test"), text("Male"), text("9001431430"));

        $("#example-modal-sizes-title-lg").shouldBe(visible).shouldHave(exactText("Thanks for submitting the form"));
        $(byXpath("//tbody[@id='resultBody']//td[text()='Student Name']/following::td")).shouldHave(text("Auto Test"));
        $(byXpath("//tbody[@id='resultBody']//td[text()='Student Email']/following::td")).shouldHave(text("-"));
        $(byXpath("//tbody[@id='resultBody']//td[text()='Gender']/following::td")).shouldHave(text("Male"));
        $(byXpath("//tbody[@id='resultBody']//td[text()='Mobile']/following::td")).shouldHave(text("9001431430"));
        $(byXpath("//tbody[@id='resultBody']//td[text()='Date of Birth']/following::td")).shouldHave(text("-"));
        $(byXpath("//tbody[@id='resultBody']//td[text()='Subjects']/following::td")).shouldHave(text("-"));
        $(byXpath("//tbody[@id='resultBody']//td[text()='Hobbies']/following::td")).shouldHave(text("-"));
        $(byXpath("//tbody[@id='resultBody']//td[text()='Picture']/following::td")).shouldHave(text("-"));
        $(byXpath("//tbody[@id='resultBody']//td[text()='Address']/following::td")).shouldHave(text("-"));
        $(byXpath("//tbody[@id='resultBody']//td[text()='State and City']/following::td")).shouldHave(text("-"));
    }

    @Test
    void checkNumberOfDigitsMobileTest() {
        open("/automation-practice-form");
        $("p").shouldHave(exactText("Student Registration Form"));
        $("#fixedban button[aria-label='Close']").shouldBe(visible).click();
        $("input[id=userNumber]").setValue("900143143");
        $("input[id=firstName]").setValue("Auto");
        $("input[id=lastName]").setValue("Test");
        $("input[id=gender-radio-1]").click();

//        Проверка, что в поле помещается только 10 смволов?
//        $("input[id=userNumber]").setValue("90014314301");
//        $("input[id=userNumber]").shouldHave(exactText("9001431430"));

        $("button[id='submit']").scrollTo().click();
        $("#formError").shouldBe(visible)
                .shouldHave(exactText("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test
    void checkLettersInMobileTest() {
        open("/automation-practice-form");
        $("p").shouldHave(exactText("Student Registration Form"));
        $("#fixedban button[aria-label='Close']").shouldBe(visible).click();
        $("input[id=userNumber]").setValue("900143143h");
        $("input[id=firstName]").setValue("Auto");
        $("input[id=lastName]").setValue("Test");
        $("input[id=gender-radio-1]").click();

//        Проверка, что в поле помещается только 10 смволов?
//        $("input[id=userNumber]").setValue("90014314301");
//        $("input[id=userNumber]").shouldHave(exactText("9001431430"));

        $("button[id='submit']").scrollTo().click();
        $("#formError").shouldBe(visible)
                .shouldHave(exactText("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test
    void checkEmptyFieldsTest() {
        open("/automation-practice-form");
        $("p").shouldHave(exactText("Student Registration Form"));
        $("#fixedban button[aria-label='Close']").shouldBe(visible).click();

        $("#submit").scrollTo().click();
        $("#formError").shouldBe(visible)
                .shouldHave(exactText("Please fill required fields and enter a valid 10-digit mobile number."));
    }
}
