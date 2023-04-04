package lib.book.specs;

import lib.book.pojo.RequestModel;
import lib.book.pojo.ResponseModel;

import static io.restassured.RestAssured.given;
import static lib.book.helpers.Generators.getAllDataForPut;
import static lib.book.specs.LoginSpecs.*;

public class ApiSteps {

    public ResponseModel createNewBook(RequestModel data) {
        ResponseModel responseOfPost = given(request)
                .body(data)
                .when()
                .post("/books")
                .then()
                .log().status()
                .spec(response201)
                .extract().as(ResponseModel.class);
        return responseOfPost;
    }

    public ResponseModel createNewBook() {
        RequestModel data = getAllDataForPut();
        ResponseModel responseOfPost = given(request)
                .body(data)
                .when()
                .post("/books")
                .then()
                .log().status()
                .spec(response201)
                .extract().as(ResponseModel.class);
        return responseOfPost;
    }

    public ResponseModel editBook(Integer id) {
        RequestModel put = getAllDataForPut();
        ResponseModel responseOfPut = given(request)
                .body(put)
                .when()
                .put("/books/" + id.toString())
                .then()
                .log().status()
                .spec(response200)
                .extract().as(ResponseModel.class);
        return responseOfPut;
    }

    public void deleteBook(Integer id) {
        given(request)
                .when()
                .delete("/books/"+ id.toString())
                .then()
                .log().status()
                .spec(response200);
    }
}
