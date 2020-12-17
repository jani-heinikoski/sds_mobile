/*
* Author: Jani Heinikoski
* Created: 14.12.2020
 */
package com.lut.memorylane.ui;

import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

public interface IFragmentOwner {
    void changeFragment(Fragment newFragment, boolean addToBackStack);
    void changeActionbar(@DrawableRes int iconDrawableID, @StringRes int titleStringResID, View.OnClickListener onClickListener);
    void popBackStack();
}
