package lib.book.tests;

import lib.book.models.RequestModel;
import lib.book.models.ResponseModel;
import lib.book.specs.LoginSpecs;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static lib.book.helpers.Generators.getAllDataForRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class PositiveTests extends LoginSpecs {

    @Test
    void checkGettingOfAllBooks() {
        given(request)
                .when()
                .get("/books")
                .then()
                .log().status()
                .spec(response200);
    }

    @Test
    void checkGettingBookById() {
        //Arrange
        ResponseModel responseModel = apiSteps.createNewBook();
        //Act
        given(request)
                .when()
                .get("/books/" + responseModel.getBook().getId())
                //Assert
                .then()
                .log().status()
                .spec(response200);
        //Cleanup
        apiSteps.deleteBook(responseModel.getBook().getId());
    }

    @Test
    void checkGettingThatResponseReturnsAllPostFields() {
        //Arrange
        RequestModel requestModel = getAllDataForRequest();
        //Act
        ResponseModel responseModel = apiSteps.createNewBook(requestModel);
        ResponseModel responseOfGetting = apiSteps.getBookById(responseModel.getBook().getId());
        //Assert
        assertThat(requestModel.getName()).isEqualTo(responseOfGetting.getBook().getName());
        assertThat(requestModel.getAuthor()).isEqualTo(responseOfGetting.getBook().getAuthor());
        assertThat(requestModel.getIsElectronicBook()).isEqualTo(responseModel.getBook().getIsElectronicBook());
        assertThat(requestModel.getYear()).isEqualTo(responseModel.getBook().getYear());
        //Cleanup
        apiSteps.deleteBook(responseModel.getBook().getId());
    }

    @Test
    void checkThatBookCanBeCreated() {
        //Act
        ResponseModel responseModel = apiSteps.createNewBook();
        //Assert
        apiSteps.assertSuccessfulGetting(responseModel.getBook().getId());
        //Cleanup
        apiSteps.deleteBook(responseModel.getBook().getId());
    }

    @Test
    void updateInfoTest() {
        //Arrange
        ResponseModel responseOfPost = apiSteps.createNewBook();
        //Act
        ResponseModel responseOfPut = apiSteps.editBook(responseOfPost.getBook().getId());
        //Assert
        assertThat(responseOfPut.getBook().getName()).isNotEqualTo(responseOfPost.getBook().getName());
        //Cleanup
        apiSteps.deleteBook(responseOfPost.getBook().getId());
    }

    @Test
    void checkDeletionOfBook() {
        //Arrange
        ResponseModel responseModel = apiSteps.createNewBook();
        //Act
        apiSteps.deleteBook(responseModel.getBook().getId());
        //Assert
        apiSteps.assertErrorOfGetting(responseModel.getBook().getId());
    }
}

