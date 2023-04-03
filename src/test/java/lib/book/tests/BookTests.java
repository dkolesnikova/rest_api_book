package lib.book.tests;

import lib.book.lombok.LoginBodyLombokModel;
import lib.book.lombok.LoginResponseLombokModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static lib.book.specs.LoginSpecs.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BookTests {
    @Test
    void bookGettingTest() {
        given(request)
                .when()
                .get("/books")
                .then()
                .log().status()
                .spec(response200);
    }

    @Test
    void getBookByIdTest() {
        given(request)
                .when()
                .get("/books/2")
                .then()
                .log().status()
                .spec(response200);
    }

    @Test
    void addNewBookTest() {
        LoginBodyLombokModel data = new LoginBodyLombokModel();
        data.setName("Mirror");

        LoginResponseLombokModel response = given(request)
                .body(data)
                .when()
                .post("/books")
                .then()
                .log().status()
                .spec(response201)
                .extract().as(LoginResponseLombokModel.class);
        assertThat(response.getName()).isEqualTo("Mirror");
    }

    @Test
    void updateInfoTest() {
        LoginBodyLombokModel data = new LoginBodyLombokModel();
        data.setName("History");
        data.setYear("1997");
        data.setAuthor("Ivanov");
        data.setIsElectronicBook("false");

        LoginResponseLombokModel response = given(request)
                .body(data)
                .when()
                .put("/books/5")
                .then()
                .log().status()
                .spec(response201)
                .extract().as(LoginResponseLombokModel.class);
        assertThat(response.getName()).isEqualTo("History");
    }

    @Test
    void deleteTest() {
        given(request)
                .when()
                .delete("/books/3")
                .then()
                .log().status()
                .spec(response200);
    }
}

