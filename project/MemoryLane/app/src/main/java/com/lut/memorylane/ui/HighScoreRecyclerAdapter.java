/*
 * Author: Jani Heinikoski
 * Created: 25.12.2020
 * Last updated: see github repository https://github.com/jani-heinikoski/sds_mobile#sds_mobile
 * Sources:
 * */
package com.lut.memorylane.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lut.memorylane.R;
import com.lut.memorylane.data.HighScore;

import java.util.List;
import java.util.Locale;

public final class HighScoreRecyclerAdapter extends RecyclerView.Adapter<HighScoreRecyclerAdapter.CardViewHolder> {

    private List<HighScore> highScores;

    // Provide a reference to the text views for each itemView
    public static final class CardViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewScore;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.cardview_high_score_name);
            textViewScore = itemView.findViewById(R.id.cardview_high_score_score);
        }
    }

    public HighScoreRecyclerAdapter(@NonNull List<HighScore> highScores) {
        this.highScores = highScores;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_high_score, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        if (position != RecyclerView.NO_POSITION) {
            HighScore highScore = highScores.get(position);

            holder.textViewName.setText(highScore.getTimestamp());
            holder.textViewScore.setText(
                    String.format(Locale.getDefault(), "%3d", highScore.getScore())
            );
        }
    }

    public void changeHighScores(List<HighScore> newHighScores) {
        this.highScores = newHighScores;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (highScores == null) {
            return 0;
        } else {
            return highScores.size();
        }
    }

}
