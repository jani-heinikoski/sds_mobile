/*
* Author: Jani Heinikoski
* Created: 14.12.2020
 */
package com.lut.memorylane.ui;

import androidx.fragment.app.Fragment;

public interface IFragmentOwner {
    void changeFragment(Fragment newFragment, boolean addToBackStack);
}
