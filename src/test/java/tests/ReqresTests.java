package tests;

import models.lombok.LoginBodyLombokModel;
import models.lombok.LoginResponseLombokModel;
import models.lombok.UserBodyLombokModel;
import models.lombok.UserResponseLombokModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Type;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.LoginSpec.loginRequestSpec;
import static specs.LoginSpec.loginResponseSpec;
import static specs.UserSpec.*;


public class ReqresTests extends TestBase{

    @Test
    void successfulLoginWithSpecsTest() {
        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseLombokModel response = step("Make request", ()->
                given(loginRequestSpec)
                        .body(authData)

                        .when()
                        .post()

                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseLombokModel.class));

                step("Check response", ()->
                assertEquals("QpwL5tke4Pnpja7X4", response.getToken()));
    }

    @Test
    @DisplayName("Успешное создание пользователя")
    void createUserCustomAllureTest() {
        UserBodyLombokModel authData = new UserBodyLombokModel();
        authData.setName("morpheus");
        authData.setJob("leader");

        UserResponseLombokModel response = given(userRequestSpec)
                .body(authData)

                .when()
                .post()

                .then()
                .spec(userResponseSpec)
                .extract().as((Type) UserResponseLombokModel.class);
                 assertEquals("morpheus", response.getName());
                 assertEquals("leader", response.getJob());
    }


    @Test
    @DisplayName("Создание пользователя без обязательных полей")
    void createUser11Test() {
        UserBodyLombokModel authData = new UserBodyLombokModel();

        UserResponseLombokModel response = given(userRequestSpec)
                .body(authData)

                .when()
                .post()

                .then()
                .spec(userBadResponseSpec)
                .extract().as((Type) UserResponseLombokModel.class);
    }

}
