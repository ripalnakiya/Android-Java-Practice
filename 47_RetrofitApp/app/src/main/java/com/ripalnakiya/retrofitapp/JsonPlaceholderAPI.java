package com.ripalnakiya.retrofitapp;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

// Retrofit will automatically Implement these interfaces
public interface JsonPlaceholderAPI {

    @GET("posts")
    Call<List<Post>> getPosts();

    // Directly get results from URL
    @GET
    Call<List<Post>> getPosts(@Url String url);

//    @GET("posts/{id}/comments")
//    Call<List<Comment>> getComments(@Path("id") int postId);

    // For single Query
    @GET("comments")
    Call<List<Comment>> getComments(@Query("postId") int postId);

    // For multiple Queries
    @GET("comments")
    Call<List<Comment>> getComments(
            @Query("postId") Integer[] postId,
            @Query("_sort") String sortBy,
            @Query("_order") String orderBy
    );
    // Use of wrapper classes (Integer) allows the values to be nullable
    // We can query multiple values by declaring the Query as array

    // Alternate way for Multiple Queries
    @GET("comments")
    Call<List<Comment>> getComments(@QueryMap Map<String, String> parameters);

    @POST("posts")
    Call<Post> createPost(@Body Post post);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String> fields);

    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id, @Body Post post);
    // We can also use @Field and @FieldMap with PUT request

    // PUT request with Headers and Header
//    @Headers({"Static-Header1: 123", "Static-Header2: 456"})
//    @PUT("posts/{id}")
//    Call<Post> putPost(
//            @Header("Dynamic-Header") String header,
//            @Path("id") int id,
//            @Body Post post
//    );

    // PUT request with HeaderMap
//    @PUT("posts/{id}")
//    Call<Post> putPost(
//            @HeaderMap Map<String, String> headers,
//            @Path("id") int id,
//            @Body Post post
//    );

    // These methods only add header to that particular request
    // We can add header for all the requests by adding new interceptor along with loggingInterceptor

    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id, @Body Post post);
    // We can also use @Field and @FieldMap with PATCH request

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);

}
