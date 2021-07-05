package homeWorkEight.test;

import homeWorkEight.Service.UserApi;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class GetByUserName {
    private UserApi userApi = new UserApi();

    @Test
    public void getUserName(){
        int id = 11;
        String email = "123455@mail.ru";
        String firstName = "Ivan";
        String lastName = "Popov";
        String password = "123456";
        String username = "popov.i";
        String phone = "85946665544";

        userApi.getByUsername(username)
                .then()
                .body("email", equalTo(email))
                .body("firstName", equalTo(firstName))
                .body("id", equalTo(id))
                .body("lastName", equalTo(lastName))
                .body("password", equalTo(password))
                .body("phone", equalTo(phone))
                .body("username", equalTo(username));
    }
    @Test
    public void getNotUserName(){
        String username = "pov.i";

        userApi.getByUsername(username)
                .then()
                .body("username", not(username));
    }

    @Test
    public void getUserNameNotId(){
        String email = "1qasa5@mail.ru";
        String firstName = "Petr";
        String lastName = "Ivanov";
        String password = "qwas1231Un";
        String username = "ivanov.p";
        String phone = "89067885544";

        userApi.getByUsername(username)
                .then()
                .body("email", equalTo(email))
                .body("firstName", equalTo(firstName))
                .body("lastName", equalTo(lastName))
                .body("password", equalTo(password))
                .body("phone", equalTo(phone))
                .body("username", equalTo(username));
    }
}
