package com.mixailsednev.storeproject.view.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class BaseFragment<Presenter extends BasePresenter> extends Fragment {

    private static String ARG_RESTORED = "RESTORED";
    protected Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = createPresenter();
    }

    public abstract Presenter createPresenter();

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        createPresenter().subscribeToDataStore();

        if (savedInstanceState == null || !savedInstanceState.getBoolean(ARG_RESTORED)) {
            onNewViewStateInstance();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        createPresenter().unSubscribeFromDataStore();

        outState.putBoolean(ARG_RESTORED, true);
    }

    /**
     * Called if a new state has been created because no viewstate from a previous
     * Activity or Fragment instance could be restored.
     * <p><b>Typically this is called on the first time the <i>Activity</i> or <i>Fragment</i> starts
     * and therefore no view state instance previously exists</b></p>
     */
    public void onNewViewStateInstance() {

    }
}
