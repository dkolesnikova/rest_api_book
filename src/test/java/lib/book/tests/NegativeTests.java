package lib.book.tests;

import lib.book.models.RequestModel;
import lib.book.models.ResponseModel;
import lib.book.specs.LoginSpecs;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static lib.book.helpers.Generators.getAllDataForRequest;


public class NegativeTests extends LoginSpecs {

    public static final SecureRandom random = new SecureRandom();

    @Test
    public void checkAddingBookWithEmptyName() {
        RequestModel data = getAllDataForRequest();
        data.setName("   ");
        ResponseModel responseModel = apiSteps.createNewBook(data);
        apiSteps.assertErrorOfGetting(responseModel.getBook().getId());
    }
    @Test
    public void checkAddingBookWithNegativeYear() {
        RequestModel data = getAllDataForRequest();
        data.setYear(-100);
        ResponseModel responseModel = apiSteps.createNewBook(data);
        apiSteps.assertErrorOfGetting(responseModel.getBook().getId());
    }

    @Test
    public void checkGettingBookWithInvalidId() {
        int invalidId = random.nextInt((300 - 200) + 1) + 200;
        apiSteps.assertErrorOfGetting(invalidId);
    }

    @Test
    public void checkUpdatingBookWithInvalidId() {
        int invalidId = random.nextInt((300 - 200) + 1) + 200;
        apiSteps.assertErrorOfPutting(invalidId, getAllDataForRequest());
    }

    @Test
    public void checkDeletionBookWithInvalidId() {
        int invalidId = random.nextInt((300 - 200) + 1) + 200;
        apiSteps.assertErrorOfDeletion(invalidId);
    }


}
