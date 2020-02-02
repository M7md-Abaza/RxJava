package com.example.rxjava.ui.main;

import android.util.Log;

import com.example.rxjava.data.PostsClient;
import com.example.rxjava.pojo.PostModel;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class PostViewModel extends ViewModel {

    private static final String TAG = "PostViewModel";
    // This MutableLiveData<List<PostModel>> to receive the data<list> which returned from Retrofit
    MutableLiveData<List<PostModel>> postMutableLiveData = new MutableLiveData<>();

    // This getPosts() function to get posts from Retrofit
    public void getPosts() {
        Observable observable = PostsClient.getINSTANCE().getPosts()
                // to change thread from Main Thread to io to run on background because it takes long time
                .subscribeOn(Schedulers.io())
                // to manage download(Observer) stream to ba as upload stream(Observable)
                .observeOn(AndroidSchedulers.mainThread());

        Observer<List<PostModel>> observer = new Observer<List<PostModel>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<PostModel> postModels) {
                postMutableLiveData.setValue(postModels);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);

        /*

         ** in this case which we use OnNext and OnError only **

         ** we can use next line of code instied of " Observer<List<PostModel>> observer....etc. **

         observable.subscribe(o-> postMutableLiveData.setValue(o) , e-> Log.d(TAG, "onError: " + e) );

         ** o-> for OnNext && e-> for OnError **

         ** to run without any problems we must make Observable to return <List<PostModel>>
         ** and the code will ba as same as the next

         Observable<List<PostModel>> observable = PostsClient.getINSTANCE().getPosts()
         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread());


        */
    }
}
