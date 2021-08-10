package homeWorkTen;

import homeWorkTen.pojo.Resource;
import homeWorkTen.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Response;

import java.io.IOException;

import static org.springframework.util.Assert.isTrue;

@SpringBootTest
class ApiHelperApplicationTests {

    @Test
    public void getUserTest() throws IOException {
        Response<User> response;
        APIInterface client = APIClientHelper.getClient().create(APIInterface.class);
        response = client.getUserById().execute();

        isTrue(response.body().getData().getEmail().equals("janet.weaver@reqres.in"), "Email найден");
        isTrue(response.body().getData().getFirstname().equals("Janet"), "FirstName найден");
    }

    @Test
    public void getResourceTest() throws IOException {
        Response<Resource> response;
        APIInterface client = APIClientHelper.getClient().create(APIInterface.class);
        response = client.getResource().execute();

        isTrue(response.body().getData().getColor().equals("#C74375"), "Color найден");
        isTrue(response.body().getData().getName().equals("fuchsia rose"), "Name найдено");
        isTrue(response.body().getData().getPantoneValue().equals("17-2031"), "PantoneValue найден");
    }
}