package com.example.bookwork2;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    public static final String BASE_URL = "https://www.googleapis.com/";

    @GET("books/v1/volumes")
    Call<ResponseBody> searchByQuery(@Query("q") String query, @Query("maxResults") String max);

    

}
