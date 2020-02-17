package com.anup.myapplication.api;

import com.anup.myapplication.models.ImageFile;
import com.anup.myapplication.models.Journal;
import com.anup.myapplication.models.Result;
import com.anup.myapplication.models.Vault;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/*
This is a Retrofit Interface Class.
*/
public interface ApiClient {

// //////////////////////////////////////////////////////////////////////
//........................GET METHOD ..................................
    @GET("journals")
    Call<List<Journal>> getJournals(
            @Header("Cookie") String cookie
    ); // Final

    @GET("vaults")
    Call<List<Vault>> getVaults(
            @Header("Cookie") String cookie
    ); // Final

// //////////////////////////////////////////////////////////////////////
//........................POST METHOD ..................................
    @FormUrlEncoded
    @POST("users/login")
    @Headers("Content-type:application/x-www-form-urlencoded")
    Call<Result> Login(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("users/signup")
    @Headers("Content-type:application/x-www-form-urlencoded")
    Call<Result> Signup(
            @Field("name") String name,
            @Field("contact") String contact,
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/journals")
    @Headers("Content-type:application/x-www-form-urlencoded")
    Call<Journal> PostJournal (
            @Field("name") String name,
            @Field("info") String Journal
    );

    @Multipart
    @POST("upload")
    Call<ImageFile> UploadImage(
            @Header("Cookie") String cookie,
            @Part MultipartBody.Part img
    );
}