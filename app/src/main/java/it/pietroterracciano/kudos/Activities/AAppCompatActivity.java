package it.pietroterracciano.kudos.Activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import it.pietroterracciano.kudos.Kudos;

public class AAppCompatActivity extends AppCompatActivity
{
    /*
    @NonNull
    public final ActivityModules Module;

    public AAppCompatActivity()
    {
        Module = new ActivityModules();
    }*/

    @Override
    protected void onCreate(Bundle oBundle)
    {
        super.onCreate(oBundle);
        invalidateReferences();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        invalidateReferences();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        invalidateReferences();
    }

    private void invalidateReferences()
    {
        Kudos.registerActivity(this);
    }
}