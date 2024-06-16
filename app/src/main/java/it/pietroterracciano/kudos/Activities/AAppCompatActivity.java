package it.pietroterracciano.kudos.Activities;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Constructor;

import it.pietroterracciano.kudos.Behaviors.ActivityBehaviour;
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
        onBehaviourReceive(ActivityBehaviour);
        onCreateReceive(bnd);
    }

    protected abstract void onBehaviourReceive(@NonNull ActivityBehaviour ab);
    protected abstract void onCreateReceive(@Nullable Bundle bnd);

    @Override
    protected final void onStart()
    {
        super.onStart();
        _invalidateReferences();
        onStartReceive();
    }

    protected abstract void onStartReceive();

    @Override
    protected final void onResume()
    {
        super.onResume();
        _invalidateReferences();
        onResumeReceive();
    }

    protected abstract void onResumeReceive();

    private void _invalidateReferences()
    {
        Kudos.registerActivity(this);
    }

    @Override
    protected final void onPause()
    {
        onPauseReceive();
        super.onPause();
    }

    protected abstract void onPauseReceive();

    @Override
    protected final void onStop()
    {
        onStopReceive();
        super.onStop();
    }

    protected abstract void onStopReceive();
}