package com.example.rxjava.data;

import com.example.rxjava.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PostInterface {

    @GET("posts")
    //make sur that Observable comes from reactiveX
    // we can use Single<> insted of Observable<> in this case only because the data get
    // from server on one time so we don't need OnNext every time
    Observable<List<PostModel>> getPosts();
}
