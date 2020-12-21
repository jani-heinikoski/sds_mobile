package com.lut.memorylane.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

public class GameProgressBar extends ProgressBar {

    private IProgressCallback iProgressCallback = null;

    public GameProgressBar(Context context) {
        super(context);
    }

    public GameProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setProgress(int progress) {
        super.setProgress(progress);
        if (progress == this.getMax() && this.iProgressCallback != null) {
            iProgressCallback.progressbarFinished();
        }
    }

    public void setIProgressCallback(@NonNull IProgressCallback callback) {
        this.iProgressCallback = callback;
    }
}
