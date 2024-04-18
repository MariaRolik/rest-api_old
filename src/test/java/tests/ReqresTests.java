package tests;

import models.lombok.*;
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
    void createUserTest() {
        UserBodyLombokModel authData = new UserBodyLombokModel();
        authData.setName("morpheus");
        authData.setJob("leader");

        CreateUserResponseLombokModel response = given(userRequestSpec)
                .body(authData)

                .when()
                .post()

                .then()
                .spec(userResponseSpec)
                .extract().as((Type) CreateUserResponseLombokModel.class);
                 assertEquals("morpheus", response.getName());
                 assertEquals("leader", response.getJob());
    }


    @Test
    @DisplayName("Создание пользователя с пустыми значениями")
    void createEmptyUserTest() {
        UserBodyLombokModel authData = new UserBodyLombokModel();

        CreateUserResponseLombokModel response = given(userRequestSpec)
                .body(authData)

                .when()
                .post()

                .then()
                .spec(userBadResponseSpec)
                .extract().as((Type) CreateUserResponseLombokModel.class);
    }

    @Test
    @DisplayName("Изменение профессии пользователя c put")
    void updateUserWithPutTest() {
        UserBodyLombokModel authData = new UserBodyLombokModel();
        authData.setName("morpheus");
        authData.setJob("captain");

        UpdateUserResponseLombokModel response = given(userUpdateRequestSpec)
                .body(authData)

                .when()
                .put()

                .then()
                .spec(userUpdateResponseSpec)
                .extract().as((Type) UpdateUserResponseLombokModel.class);
        assertEquals("morpheus", response.getName());
        assertEquals("captain", response.getJob());
    }

    @Test
    @DisplayName("Изменение профессии пользователя c patch")
    void updateUserWithPatchTest() {
        UserBodyLombokModel authData = new UserBodyLombokModel();
        authData.setName("morpheus");
        authData.setJob("mentor");

        UpdateUserResponseLombokModel response = given(userUpdateRequestSpec)
                .body(authData)

                .when()
                .patch()

                .then()
                .spec(userUpdateResponseSpec)
                .extract().as((Type) UpdateUserResponseLombokModel.class);
                assertEquals("morpheus", response.getName());
                assertEquals("mentor", response.getJob());
    }

}
