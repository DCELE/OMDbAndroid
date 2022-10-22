package com.example.omdbandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.omdbandroid.DetailActivity;
import com.example.omdbandroid.R;
import com.example.omdbandroid.model.Result;
import com.squareup.picasso.Picasso;
import java.util.List;


public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.Holder> {

    private Context context;
    private List<Result> list;

    public HomeRecyclerAdapter(Context context, List<Result> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeRecyclerAdapter.Holder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.itemlist, parent, false);

        final HomeRecyclerAdapter.Holder holder = new Holder(view);
        holder.listView.setOnClickListener(v -> {
            Intent intent = new Intent(parent.getContext(), DetailActivity.class);
            Result result = new Result();
            result.setTitle(list.get(holder.getAdapterPosition()).getTitle());
            result.setOverview(list.get(holder.getAdapterPosition()).getOverview());
            result.setPosterPath(list.get(holder.getAdapterPosition()).getPosterPath());
            result.setReleaseDate(list.get(holder.getAdapterPosition()).getReleaseDate());
            result.setVoteAverage(list.get(holder.getAdapterPosition()).getVoteAverage());
            intent.putExtra(DetailActivity.extra, result);
            parent.getContext().startActivity(intent);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerAdapter.Holder holder, int position) {
        holder.movieTitle.setText(list.get(position).getTitle());
        holder.movieRelease.setText(list.get(position).getReleaseDate());
        Picasso.get().load("https://image.tmdb.org/t/p/w185" + list.get(position).getPosterPath()).into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView movieTitle, movieRelease;
        ImageView moviePoster;
        RelativeLayout listView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movieTitleMain);
            movieRelease = itemView.findViewById(R.id.movieReleaseMain);
            moviePoster = itemView.findViewById(R.id.moviePosterMain);
            listView = itemView.findViewById(R.id.listView);
        }
    }
}
