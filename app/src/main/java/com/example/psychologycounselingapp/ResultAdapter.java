package com.example.psychologycounselingapp;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private List<Result> resultList;
    private Context context;
    public ResultAdapter(Context context, List<Result> resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.itemresult, null);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        Result result = resultList.get(position);
        holder.textViewAge.setText(result.getTest_age());
        holder.textViewScore.setText(result.getTest_score());
        holder.textViewMobile.setText(result.getUser_mobile());
        if(result.getProgress_result() > 100) {
            holder.progressBarResult.setProgress(result.getProgress_result());
            ObjectAnimator objectAnimator = ObjectAnimator.ofInt(holder.progressBarResult,"progress",  0, result.getProgress_result());
            objectAnimator.setDuration(1000);
            objectAnimator.start();
            holder.progressBarResult.setProgressTintList(ColorStateList.valueOf(Color.rgb(34, 139, 34)));
        } else if(result.getProgress_result() == 100) {
            holder.progressBarResult.setProgress(result.getProgress_result());
        } else if(result.getProgress_result() < 100) {
            holder.progressBarResult.setProgress(result.getProgress_result());
            ObjectAnimator objectAnimator = ObjectAnimator.ofInt(holder.progressBarResult,"progress", 0, result.getProgress_result());
            objectAnimator.setDuration(1000);
            objectAnimator.start();
            holder.progressBarResult.setProgressTintList(ColorStateList.valueOf(Color.rgb(255, 0, 0)));
        }
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder{
        TextView textViewAge, textViewScore, textViewMobile;
        ProgressBar progressBarResult;
        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAge = itemView.findViewById(R.id.txt_age);
            textViewScore = itemView.findViewById(R.id.txt_score);
            textViewMobile = itemView.findViewById(R.id.txt_mobile);
            progressBarResult = itemView.findViewById(R.id.progress_result);
        }
    }
}
