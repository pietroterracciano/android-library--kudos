package it.pietroterracciano.kudos;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class Kudos
{
    @NonNull
    private static final Object _oLock = new Object();
    @Nullable
    private static Context _oContext;
    @Nullable
    private static Activity _oActivity;
    @Nullable
    private static Resources _oResources;
    @NonNull
    private static int _iTransientID;

    public static void setActivity(@Nullable Activity oActivity)
    {
        _oActivity = oActivity;
        setContext(oActivity);
    }
    @Nullable
    public static Activity getActivity()
    {
        return _oActivity;
    }

    public static void setContext(@Nullable Context oContext)
    {
        _oContext = oContext;
        _oResources = _oContext != null ? _oContext.getResources() : null;
    }
    @Nullable
    public static Context getContext()
    {
        return _oContext;
    }
    @Nullable
    public static Resources getResources() { return _oResources; }

    @NonNull
    public static int newTransientID()
    {
        synchronized (_oLock)
        {
            return ++_iTransientID;
        }
    }
}