package homeWorkTen;

import homeWorkTen.pojo.Resource;
import homeWorkTen.pojo.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("users/2")
    Call<User> getUserById();

    @GET("unknown/2")
    Call<Resource> getResource();
}