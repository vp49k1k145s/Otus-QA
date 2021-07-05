package homeWorkEight.test;

import homeWorkEight.Service.UserApi;
import homeWorkEight.dto.CreateUser;
import homeWorkEight.dto.CreateUserResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;


public class CreateNewUserTest {
    private UserApi userApi = new UserApi();

    @Test
    public void createNewUser() {
        CreateUser createUser = CreateUser.builder()
                .userStatus(200)
                .email("123455@mail.ru")
                .id(11)
                .firstName("Ivan")
                .lastName("Popov")
                .password("123456")
                .username("popov.i")
                .phone("85946665544")
                .build();

        Response response = userApi.createUser(createUser);


        response
                .then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo("11"));

        String messageExpectedDto = response.as(CreateUserResponse.class).getMessage();
        Assertions.assertEquals("11", messageExpectedDto);
    }

    @Test
    public void createNewUserNotId() {
        CreateUser createUser = CreateUser.builder()
                .userStatus(200)
                .email("1qasa5@mail.ru")
                .firstName("Petr")
                .lastName("Ivanov")
                .password("qwas1231Un")
                .username("ivanov.p")
                .phone("89067885544")
                .build();

        Response response = userApi.createUser(createUser);


        response
                .then()
                .log().all()
                .statusCode(200);

        String messageExpectedDto = response.as(CreateUserResponse.class).getMessage();
        Assertions.assertNotEquals("0", messageExpectedDto);

    }
}
