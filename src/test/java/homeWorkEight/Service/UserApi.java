package homeWorkEight.Service;

import homeWorkEight.dto.CreateUser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String BASE_URI="https://petstore.swagger.io/v2";
    private static final String PATH = "/user";
    private final RequestSpecification requestSpecification;
    public static final String PATH_BY_USERNAME = "/user/{username}";

    public UserApi () {
        requestSpecification = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .log().all();

    }
    public Response createUser (CreateUser user) {
        return
                given(requestSpecification)
                        .basePath(PATH)
                        .body(user)
                        .when()
                        .post();

    }
    public Response getByUsername(String username){
        return
                given(requestSpecification)
                        .pathParam("username", username)
                        .basePath(PATH_BY_USERNAME)
                        .when()
                        .get();
    }
}
