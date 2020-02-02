package com.example.rxjava.ui.main;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rxjava.R;
import com.example.rxjava.pojo.PostModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {

    private List<PostModel> PostsList = new ArrayList<>();

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.titleTV.setText(PostsList.get(position).getTitle());
        holder.userTV.setText(PostsList.get(position).getUserId() + "");
        holder.bodyTV.setText(PostsList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return PostsList.size();
    }

    // setList Function data pass to it then it pass data to PostsList on the Holder
    void setList(List<PostModel> PostsList) {
        this.PostsList = PostsList;
        notifyDataSetChanged();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        TextView titleTV, userTV, bodyTV;

        PostViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.tv_title);
            userTV = itemView.findViewById(R.id.tv_userId);
            bodyTV = itemView.findViewById(R.id.tv_body);
        }
    }
}
