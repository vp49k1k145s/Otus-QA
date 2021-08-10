package homeWorkTen;

import homeWorkTen.pojo.Login;
import homeWorkTen.pojo.Register;
import homeWorkTen.pojo.Resource;
import homeWorkTen.pojo.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {
    @GET("users/2")
    Call<User> getUserById();

    @GET("unknown/2")
    Call<Resource> getResource();

    @POST("login")
    Call<Login> login(@Body Register register);
}