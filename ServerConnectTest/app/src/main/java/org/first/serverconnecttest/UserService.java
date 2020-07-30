package org.first.serverconnecttest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("/user/insert")
    Call<User> insertUserInfo (@Body User user);

    @POST("/user/update/{userId}")
    Call<User> updateUserInfo (@Path("userId") String id, @Body User user);

    @GET("/user/find/{userId}")
    Call<User> findUser(@Path("userId") String id);

    @GET("/user/findAll")
    Call<List<User>> findAllUser();

    @POST("/user/delete/{userId}")
    Call<User> deleteUserInfo(@Path("userId") String id);


}
