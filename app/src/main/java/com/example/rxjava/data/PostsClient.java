package com.example.rxjava.data;

import com.example.rxjava.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsClient {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private PostInterface postInterface;
    private static PostsClient INSTANCE;

    // to avoid repeat this code every time using Retrofit
    private PostsClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //Next line to convert from <Call> to <observable> so we will change interface Method form Call to observable
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        postInterface = retrofit.create(PostInterface.class);
    }

    //To Make sure if PostsClient is already import or not and create PostsClient if it didn't imported
    public static PostsClient getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new PostsClient();
        }
        return INSTANCE;
    }

    // we can use Single<> insted of Observable<> in this case only because the data get
    // from server on one time so we don't need OnNext every time
    public Observable<List<PostModel>> getPosts() {
        return postInterface.getPosts();
    }

}
