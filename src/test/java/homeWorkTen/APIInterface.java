package homeWorkTen;

import homeWorkTen.pojo.Account;
import homeWorkTen.pojo.LoginOut;
import les22.retrofit.pojo.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {
    @POST("login")
    Call<LoginOut> login(@Body Register register);

    @GET("users/5")
    Call<Account> getUserById();

    @GET("unknown/2")
    Call<Resource> getResource();

    @POST("register")
    Call<RegisterOut> register(@Body Register register);
}