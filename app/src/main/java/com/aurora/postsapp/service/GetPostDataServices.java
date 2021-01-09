package com.aurora.postsapp.service;

import com.aurora.postsapp.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetPostDataServices {

    @GET("/posts")
    Call<List<Post>>getPostAll();
}
