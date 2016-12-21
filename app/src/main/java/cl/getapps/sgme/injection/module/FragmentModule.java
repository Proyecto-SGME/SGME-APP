package cl.getapps.sgme.injection.module;

import android.content.Context;
import android.support.v4.app.Fragment;

import cl.getapps.sgme.injection.ActivityContext;
import dagger.Module;
import dagger.Provides;

/**
 * Creado por GRINGRAZ el 21-12-2016.
 */

@Module
public class FragmentModule  {

    private Fragment mFragment;

    public FragmentModule(Fragment mFragment) {
        this.mFragment = mFragment;
    }

    @Provides
    Fragment provideFragment(){ return mFragment; }

    @Provides
    @ActivityContext
    Context providesContext(){ return mFragment.getActivity(); }
}
