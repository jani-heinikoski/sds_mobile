/*
 * Author: Jani Heinikoski
 * Created: 17.12.2020
 * Last updated: see github repository https://github.com/jani-heinikoski/sds_mobile#sds_mobile
 * Sources:
 * */
package com.lut.memorylane.utility;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lut.memorylane.ui.IFragmentOwner;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<Integer> actionBarTitleID = new MutableLiveData<>();
    private final MutableLiveData<Integer> actionBarIconID = new MutableLiveData<>();

    private final MutableLiveData<View.OnClickListener> actionBarButtonOnClickListener = new MutableLiveData<>();
    private final MutableLiveData<IFragmentOwner> fragmentOwner = new MutableLiveData<>();

    public LiveData<Integer> getActionBarTitleID() {
       return actionBarTitleID;
    }

    public LiveData<Integer> getActionBarIconID() {
        return actionBarIconID;
    }

    public LiveData<View.OnClickListener> getActionBarButtonOnClickListener() {
        return actionBarButtonOnClickListener;
    }

    public void setActionBarTitleID(@NonNull Integer titleID) {
        actionBarTitleID.setValue(titleID);
    }

    public void setActionBarIconID(@NonNull Integer iconID) {
        actionBarIconID.setValue(iconID);
    }

    public void setActionBarButtonOnClickListener(View.OnClickListener listener) {
        actionBarButtonOnClickListener.setValue(listener);
    }

    public LiveData<IFragmentOwner> getFragmentOwner() {
        return fragmentOwner;
    }

    public void setFragmentOwner(@NonNull IFragmentOwner fragmentOwner) {
        this.fragmentOwner.setValue(fragmentOwner);
    }

}
