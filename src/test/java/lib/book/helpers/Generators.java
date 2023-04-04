package lib.book.helpers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.javafaker.Faker;
import lib.book.pojo.RequestModel;
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

    public static RequestModel getAllDataForPut() {
        Faker faker = new Faker();
        return RequestModel
                .builder()
                .name(faker.book().title())
                .year(random.nextInt((2000 - 1900) + 1) + 1900)
                .isElectronicBook(random.nextBoolean())
                .author(faker.book().author())
                .build();
    }
}
