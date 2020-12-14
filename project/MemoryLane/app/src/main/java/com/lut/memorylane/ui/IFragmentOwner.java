package com.lut.memorylane.ui;

import androidx.fragment.app.Fragment;

public interface IFragmentOwner {
    void changeFragment(Fragment newFragment, boolean addToBackStack);
}
