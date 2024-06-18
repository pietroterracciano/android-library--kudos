package it.pietroterracciano.kudos.Activities;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.IdRes;
import androidx.annotation.IntegerRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;

import it.pietroterracciano.kudos.Behaviors.ActivityBehaviour;
import it.pietroterracciano.kudos.Controllers.LayoutInflaterController;
import it.pietroterracciano.kudos.Kudos;

public abstract class AAppCompatActivity extends AppCompatActivity
{
    @NonNull
    public ActivityBehaviour
        ActivityBehaviour;

    @Override
    protected final void onCreate(@Nullable Bundle bnd)
    {
        super.onCreate(bnd);
        _invalidateReferences();
        ActivityBehaviour = new it.pietroterracciano.kudos.Behaviors.ActivityBehaviour(this);
        _onBehaviourReceive(ActivityBehaviour);
        Integer ilrid = _onLayoutResourceIDInflate();
        if(ilrid != null) setContentView(ilrid);
        _onCreateReceive(bnd);
    }

    protected abstract void _onBehaviourReceive(@NonNull ActivityBehaviour ab);
    @LayoutRes
    @Nullable
    protected abstract Integer _onLayoutResourceIDInflate();
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
        return LayoutInflaterController.inflate(vg, cls, true);
    }
}