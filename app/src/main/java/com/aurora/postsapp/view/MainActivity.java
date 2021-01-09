package com.aurora.postsapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.aurora.postsapp.R;
import com.aurora.postsapp.adapter.PostsAdapter;
import com.aurora.postsapp.databinding.ActivityMainBinding;
import com.aurora.postsapp.model.Post;
import com.aurora.postsapp.service.GetPostDataServices;
import com.aurora.postsapp.service.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<Post> posts;
    ActivityMainBinding mainBinding;
    RecyclerView PostRecyclerView;
PostsAdapter postsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        mainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainBinding.setPost(new Post());

        getPost();


    }
    private void initRecycler() {

        PostRecyclerView=mainBinding.recycler;
        PostRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        PostRecyclerView.setHasFixedSize(true);
        postsAdapter=new PostsAdapter(null);
        PostRecyclerView.setAdapter(postsAdapter);
        postsAdapter.setPosts(posts);
        postsAdapter.setOnItemClickListener(new PostsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Post post) {

            }
        });



    }


    private Object getPost() {
        Log.i("TAGGETPOST", "getPost start");
        GetPostDataServices getPostDataServices = RetrofitClientInstance.getRetrofitInstance();
        Call<List<Post>> call = getPostDataServices.getPostAll();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.i("TAGGETPOST", response.code()+".....");
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        posts = (ArrayList<Post>) response.body();
                        for (Post post : posts) {
                            Log.i("TAGGETPOST", post.getTitle());
                        }
                        initRecycler();
                    }
                }else Log.i("TAGGETPOST", response.code()+"");
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.i("TAGGETPOST", t.getMessage());
            }
        });
        return posts;
    }
}