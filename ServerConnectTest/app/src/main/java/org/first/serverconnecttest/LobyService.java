package org.first.serverconnecttest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LobyService {
    @POST("/loby/insert")
    Call<Loby> insertLobyInfo(@Body Loby loby);

    @POST("/loby/update/{userId}")
    Call<Loby> updateLobyInfo(@Path("userId") String id, @Body Loby loby);

    @GET("/loby/find/{userId}")
    Call<Loby> findLoby(@Path("userId") String id);

    @GET("/loby/findAll")
    Call<List<Loby>> findAllLoby();

    @POST("/loby/delete/{userId}")
    Call<Loby> deleteLoby(@Path("userId") String id);
}
