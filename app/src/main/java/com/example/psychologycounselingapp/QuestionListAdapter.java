package com.example.psychologycounselingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.ViewHolder> {
    private Question question[];
    private FalseAnswerThresholdCrossListener falseAnswerThresholdCrossListener;

    public QuestionListAdapter(
            Question[] question,
            FalseAnswerThresholdCrossListener falseAnswerThresholdCrossListener
    ) {
        this.question = question;
        this.falseAnswerThresholdCrossListener = falseAnswerThresholdCrossListener;
    }

    int trueCounter = 0, falseCounter = 0;

    @NonNull
    @Override
    public QuestionListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionListAdapter.ViewHolder holder, final int position) {
        final Question myQuestionData = question[position];
        holder.txtQuestion.setText(question[position].getQustion());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "click on item: " + myQuestionData.getQustion(), Toast.LENGTH_LONG).show();
            }
        });
        holder.yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    question[position].setAnswer(true);
                    trueCounter++;
                    holder.yes.setEnabled(false);
                    holder.no.setEnabled(false);
                }
            }
        });
        holder.no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    question[position].setAnswer(false);
                    falseCounter++;
                    holder.yes.setEnabled(false);
                    holder.no.setEnabled(false);
                    if (falseCounter >= 2) {
                        falseAnswerThresholdCrossListener.onThresholdCrossed();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.question.length;
    }

    public int attemptedCount() { return this.trueCounter + this.falseCounter;}

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtQuestion;
        public ConstraintLayout constraintLayout;
        public RadioButton yes, no;
        public RadioGroup radioGroup;
        public ViewHolder(View itemView) {
            super(itemView);

            this.txtQuestion = (TextView) itemView.findViewById(R.id.txt_que);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.constarinLayout);
            yes = itemView.findViewById(R.id.btn_yes);
            no = itemView.findViewById(R.id.btn_no);
            radioGroup = itemView.findViewById(R.id.radioGroup);
        }
    }
}
