package cl.getapps.sgme.ui.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import cl.getapps.sgme.SgmeApplication;
import cl.getapps.sgme.injection.component.ConfigPersistentComponent;
import cl.getapps.sgme.injection.component.DaggerConfigPersistentComponent;
import cl.getapps.sgme.injection.component.FragmentComponent;
import cl.getapps.sgme.injection.module.FragmentModule;
import timber.log.Timber;

/**
 * Creado por GRINGRAZ el 21-12-2016.
 */

public class BaseFragment extends Fragment {
    private static final String                               KEY_ACTIVITY_ID = "KEY_FRAGMENT_ID";
    private static final AtomicLong                           NEXT_ID         = new AtomicLong(0);
    private static final Map<Long, ConfigPersistentComponent> sComponentsMap  = new HashMap<>();

    public ProgressDialog progressDialog;

    private FragmentComponent mFragmentComponent;
    private long              mFragmentId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mFragmentId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();
        ConfigPersistentComponent configPersistentComponent;
        if (!sComponentsMap.containsKey(mFragmentId)) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", mFragmentId);
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(SgmeApplication.get(getActivity()).getComponent())
                    .build();
            sComponentsMap.put(mFragmentId, configPersistentComponent);
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d", mFragmentId);
            configPersistentComponent = sComponentsMap.get(mFragmentId);
        }
        mFragmentComponent = configPersistentComponent.fragmentComponent(new FragmentModule(this));

        progressDialog = new ProgressDialog(getActivity());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, mFragmentId);
    }

    @Override
    public void onDestroy() {
        Timber.i("Clearing ConfigPersistentComponent id=%d", mFragmentId);
        sComponentsMap.remove(mFragmentId);
        super.onDestroy();
    }

    public void showProgressDialog(){
        progressDialog.show();
    }

    public void hideProgressDialog(){
        progressDialog.cancel();
    }

    public FragmentComponent fragmentComponent() {
        return mFragmentComponent;
    }
}
