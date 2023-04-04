package lib.book.tests;

import lib.book.pojo.RequestModel;
import lib.book.pojo.ResponseModel;
import lib.book.specs.LoginSpecs;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static lib.book.helpers.Generators.getAllDataForPut;
import static org.assertj.core.api.Assertions.assertThat;

public class BookTests extends LoginSpecs {

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
        RequestModel data = new RequestModel();
        data.setName("Mirror");

        ResponseModel response = given(request)
                .body(data)
                .when()
                .post("/books")
                .then()
                .log().status()
                .spec(response201)
                .extract().as(ResponseModel.class);

        assertThat(response.getBook().getName()).isEqualTo("Mirror");
    }

    @Test
    void updateInfoTest() {
        //Arrange
        ResponseModel responseOfPost = apiSteps.createNewBook();
        //Act
        ResponseModel responseOfPut = apiSteps.editBook(responseOfPost.getBook().getId());
        //Assert
        assertThat(responseOfPut.getBook().getName()).isNotEqualTo(responseOfPost.getBook().getName());
        //cleanup
        apiSteps.deleteBook(responseOfPost.getBook().getId());
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

