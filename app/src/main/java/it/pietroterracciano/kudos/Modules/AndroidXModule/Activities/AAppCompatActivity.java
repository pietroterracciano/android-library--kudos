package it.pietroterracciano.kudos.Modules.AndroidXModule.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import it.pietroterracciano.kudos.Behaviors.WindowBehaviour;
import it.pietroterracciano.kudos.Modules.AndroidXModule.Behaviours.ActionBarBehaviour;
import it.pietroterracciano.kudos.Utils.ActivityUtils;
import it.pietroterracciano.kudos.Utils.LayoutInflaterUtils;
import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Modules.AndroidXModule.Utils.AppCompatActivityUtils;

public abstract class AAppCompatActivity extends AppCompatActivity
{
    @Override
    protected final void onCreate(@Nullable Bundle bnd)
    {
        super.onCreate(bnd);
        _invalidateReferences();
        _onWindowBehaviourReceive(new WindowBehaviour(getWindow()));
        Integer ilr2i = _onLayoutResourceToInflate();
        if(ilr2i != null)
        {
            setContentView(ilr2i);
            Integer itid2i = _onToolbarIDToInflate();
            if(itid2i != null)
            {
                setSupportActionBar(itid2i);
                _onActionBarBehaviourReceive(new ActionBarBehaviour(getSupportActionBar()));
            }
        }
        _onCreateReceive(bnd);
        _onIntentReceive(getIntent());
    }

    public final void setSupportActionBar(@NonNull @IdRes int i)
    {
        AppCompatActivityUtils.setSupportActionBar(this, i);
    }

    @Nullable @LayoutRes
    protected abstract Integer _onLayoutResourceToInflate();
    @Nullable @IdRes
    protected abstract Integer _onToolbarIDToInflate();

    protected abstract void _onIntentReceive(@Nullable Intent intent);
    protected abstract void _onActionBarBehaviourReceive(@NonNull ActionBarBehaviour abb);
    protected abstract void _onWindowBehaviourReceive(@NonNull WindowBehaviour wb);
    protected abstract void _onCreateReceive(@Nullable Bundle bnd);

    @Override
    protected final void onStart()
    {
        super.onStart();
        _invalidateReferences();
        _onStartReceive();
    }

    protected abstract void _onStartReceive();

    @Override
    protected final void onResume()
    {
        super.onResume();
        _invalidateReferences();
        _onResumeReceive();
    }

    protected abstract void _onResumeReceive();

    private void _invalidateReferences()
    {
        Kudos.registerActivity(this);
    }

    @Override
    protected final void onPause()
    {
        _onPauseReceive();
        super.onPause();
    }

    protected abstract void _onPauseReceive();

    @Override
    protected final void onStop()
    {
        _onStopReceive();
        super.onStop();
    }

    protected abstract void _onStopReceive();

    @Nullable
    public final <T extends Fragment> T inflate(@IdRes @NotNull int i, @Nullable Class<T> cls)
    {
        return inflate(findViewById(i), cls);
    }
    @Nullable
    public final <T extends Fragment> T inflate(@Nullable ViewGroup vg, @Nullable Class<T> cls)
    {
        return LayoutInflaterUtils.inflate(vg, cls, true);
    }

    protected final void _startActivity(@Nullable Class<? extends Activity> cls)
    {
        ActivityUtils.start(cls);
    }
}