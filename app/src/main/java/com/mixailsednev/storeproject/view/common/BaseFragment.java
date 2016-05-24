package com.mixailsednev.storeproject.view.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public abstract class BaseFragment<Presenter extends BasePresenter> extends Fragment {

    protected Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public abstract Presenter createPresenter();

    public Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = createPresenter();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState == null) {
            onNewViewStateInstance();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().subscribeToDataStore();
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().unSubscribeFromDataStore();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onNewViewStateInstance() {

    }
}
