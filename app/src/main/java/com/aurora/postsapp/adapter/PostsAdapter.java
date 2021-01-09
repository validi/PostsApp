package com.aurora.postsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aurora.postsapp.R;
import com.aurora.postsapp.databinding.PostItemBinding;
import com.aurora.postsapp.model.Post;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {

    public PostsAdapter(ArrayList<Post> posts) {
        this.posts = posts;
    }

    ArrayList<Post> posts;
    public OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PostItemBinding postItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.post_item, parent, false);
        return new PostViewHolder(postItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.postItemBinding.setPost(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        PostItemBinding postItemBinding;

        public PostViewHolder(@NonNull PostItemBinding postItemBinding) {
            super(postItemBinding.getRoot());
            this.postItemBinding = postItemBinding;
            postItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int clickPosition=getAdapterPosition();
                    if(onItemClickListener!=null && clickPosition!=RecyclerView.NO_POSITION)
                        onItemClickListener.onItemClick(posts.get(clickPosition));
                }
            });
        }
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Post post);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
