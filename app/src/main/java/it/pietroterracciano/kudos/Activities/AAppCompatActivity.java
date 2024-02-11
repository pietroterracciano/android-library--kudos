package it.pietroterracciano.kudos.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import it.pietroterracciano.kudos.Kudos;

public class AAppCompatActivity extends AppCompatActivity
{
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