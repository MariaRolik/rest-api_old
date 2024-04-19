package extensions;

import io.qameta.allure.Step;
import models.LoginRequestModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.BasicSpec.*;

public class LoginExtension implements BeforeEachCallback {
    @Override
    @Step("Авторизация через API")
    public void beforeEach(ExtensionContext context) {
        LoginRequestModel authData = new LoginRequestModel();
        authData.setUserName("mrolik");
        authData.setPassword("Mrolik123%");
        LoginResponseModel response = given(basicRequestSpec)
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(ok200ResponseSpec)
                .extract().as(LoginResponseModel.class);
        step("Открытие браузера для добавления данных авторизации в куки", () ->
                open("/favicon.ico"));
        getWebDriver().manage().addCookie(new Cookie("userID", response.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", response.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", response.getExpires()));
    }
}
