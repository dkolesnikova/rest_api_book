package lib.book.specs;

import lib.book.models.ErrorModel;
import lib.book.models.ErrorRequestModel;
import lib.book.models.RequestModel;
import lib.book.models.ResponseModel;

import static io.restassured.RestAssured.given;
import static lib.book.helpers.Generators.getAllDataForRequest;
import static lib.book.specs.LoginSpecs.*;

public class ApiSteps {

    public ResponseModel createNewBook(RequestModel data) {
        return given(request)
                .body(data)
                .when()
                .post("/books")
                .then()
                .log().status()
                .extract().as(ResponseModel.class);
    }

    public ResponseModel createNewBook(ErrorRequestModel data) {
        return given(request)
                .body(data)
                .when()
                .post("/books")
                .then()
                .log().status()
                .extract().as(ResponseModel.class);
    }

    public ResponseModel createNewBook() {
        RequestModel data = getAllDataForRequest();
        return createNewBook(data);
    }

    public ResponseModel editBook(Integer id) {
        RequestModel put = getAllDataForRequest();
        return editBook(id, put);
    }

    public ResponseModel editBook(Integer id, RequestModel put) {
        return given(request)
                .body(put)
                .when()
                .put("/books/" + id.toString())
                .then()
                .log().status()
                .extract().as(ResponseModel.class);
    }

    public ResponseModel getBookById(Integer id) {
        return given(request)
                .when()
                .get("/books/" + id.toString())
                .then()
                .log().status()
                .extract().as(ResponseModel.class);
    }

    public void deleteBook(Integer id) {
        given(request)
                .when()
                .delete("/books/" + id.toString())
                .then()
                .log().status()
                .spec(response200);
    }

    public void assertErrorOfDeletion(Integer invalidId) {
        given(request)
                .when()
                .delete("/books/" + invalidId.toString())
                .then()
                .log().status()
                .spec(response404)
                .extract().as(ErrorModel.class).getError().equals("Book with id " + invalidId + " not found");
    }

    public void assertErrorOfGetting(Integer invalidId) {
        given(request)
                .when()
                .get("/books/" + invalidId.toString())
                .then()
                .log().status()
                .spec(response404)
                .extract().as(ErrorModel.class).getError().equals("Book with id " + invalidId + " not found");
    }

    public void assertSuccessfulGetting(Integer id) {
        given(request)
                .when()
                .get("/books/" + id.toString())
                .then()
                .log().status()
                .spec(response200);
    }

    public void assertErrorOfPutting(Integer invalidId, RequestModel put) {
        given(request)
                .body(put)
                .when()
                .put("/books/" + invalidId.toString())
                .then()
                .log().status()
                .spec(response404)
                .extract().as(ErrorModel.class).equals("Book with id " + invalidId + " not found");
    }
}
