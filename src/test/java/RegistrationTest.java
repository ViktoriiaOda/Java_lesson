import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest extends TestBase {

    @Test
    void successfulRegistrationAllFieldsTest() {
        open("/automation-practice-form");
        $("#fixedban button[aria-label='Close']").shouldBe(visible).click();

        $("#firstName").setValue("Auto");
        $("#lastName").setValue("Test");
        $("#userEmail").setValue("autotest@gmail.com");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("9001431430");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOptionContainingText("2000");
        $(".react-datepicker__month-select").selectOptionContainingText("July");
        $("#datepickerDays").$(byText("11")).click();

        $("#subjectsWrapper").click();
        $$("#subjectsDropdown div").findBy(text("Arts")).scrollTo().click();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("input[type='file']").uploadFromClasspath("foto.jpg");

        $("#currentAddress").setValue("Первая строка\nВторая строка\nТретья строка\nСтрока №4");
        $("#state").click();
        $$("#stateCity-wrapper").findBy(text("Haryana")).click();
        $("#city").click();
        $$("#stateCity-wrapper").findBy(text("Karnal")).click();

        $("#submit").click();

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
        $("#fixedban button[aria-label='Close']").shouldBe(visible).click();

        $("#firstName").setValue("Auto");
        $("#lastName").setValue("Test");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("9001431430");
        $("#submit").scrollTo().click();

        $("#example-modal-sizes-title-lg").shouldBe(visible).shouldHave(exactText("Thanks for submitting the form"));
        $("#resultBody").$(byText("Student Name")).sibling(0).shouldHave(text("Auto Test"));
        $("#resultBody").$(byText("Student Email")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("Gender")).sibling(0).shouldHave(text("Male"));
        $("#resultBody").$(byText("Mobile")).sibling(0).shouldHave(text("9001431430"));
        $("#resultBody").$(byText("Date of Birth")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("Subjects")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("Hobbies")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("Picture")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("Address")).sibling(0).shouldHave(text("-"));
        $("#resultBody").$(byText("State and City")).sibling(0).shouldHave(text("-"));
    }

    @Test
    void checkNumberOfDigitsMobileTest() {
        open("/automation-practice-form");
        $("#fixedban button[aria-label='Close']").shouldBe(visible).click();
        $("#userNumber").setValue("900143143");
        $("#firstName").setValue("Auto");
        $("#lastName").setValue("Test");
        $("#genterWrapper").$(byText("Male")).click();

//        Проверка, что в поле помещается только 10 смволов?
//        $("input[id=userNumber]").setValue("90014314301");
//        $("input[id=userNumber]").shouldHave(exactText("9001431430"));

        $("#submit").scrollTo().click();
        $("#formError").shouldBe(visible)
                .shouldHave(exactText("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test
    void checkLettersInMobileTest() {
        open("/automation-practice-form");
        $("#fixedban button[aria-label='Close']").shouldBe(visible).click();
        $("#userNumber").setValue("900143143h");
        $("#firstName").setValue("Auto");
        $("#lastName").setValue("Test");
        $("#genterWrapper").$(byText("Male")).click();

        $("#submit").scrollTo().click();
        $("#formError").shouldBe(visible)
                .shouldHave(exactText("Please fill required fields and enter a valid 10-digit mobile number."));
    }

    @Test
    void checkEmptyFieldsTest() {
        open("/automation-practice-form");
        $("#fixedban button[aria-label='Close']").shouldBe(visible).click();

        $("#submit").scrollTo().click();
        $("#formError").shouldBe(visible)
                .shouldHave(exactText("Please fill required fields and enter a valid 10-digit mobile number."));
    }
}
