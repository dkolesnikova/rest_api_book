package lib.book.helpers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.javafaker.Faker;
import lib.book.models.ErrorRequestModel;
import lib.book.models.RequestModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.security.SecureRandom;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Generators {

    public static final SecureRandom random = new SecureRandom();

    public static RequestModel getAllDataForRequest() {
        Faker faker = new Faker();
        return RequestModel
                .builder()
                .name(faker.book().title())
                .year(random.nextInt((2000 - 1900) + 1) + 1900)
                .isElectronicBook(random.nextBoolean())
                .author(faker.book().author())
                .build();
    }

    public static ErrorRequestModel getAllDataForErrorRequest() {
        Faker faker = new Faker();
        return ErrorRequestModel
                .builder()
                .name(faker.book().title())
                .year(faker.random().nextDouble())
                .isElectronicBook(faker.book().author())
                .author(faker.random().nextInt((300 - 200) + 1) + 200)
                .build();
    }
}
