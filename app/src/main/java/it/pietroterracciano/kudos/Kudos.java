package it.pietroterracciano.kudos;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

import it.pietroterracciano.kudos.Utils.ObjectUtils;

public abstract class Kudos
{
    @NonNull
    private static final Object _oLock = new Object();
    @NonNull
    private static int _iTransientID;
    @Nullable
    private static Context _oContext;
    @Nullable
    private static Activity _oActivity;
    @Nullable
    private static Resources _oResources;
    @Nullable
    private static FragmentManager _oFragmentManager;
    @Nullable
    private static Lifecycle _oLifecycle;

    public static void registerActivity(@Nullable AppCompatActivity act)
    {
        if(act != null)
        {
            registerActivity((Activity) act);
            registerFragmentManager(act.getSupportFragmentManager());
            registerLifecycle(act.getLifecycle());
        }
        else
        {
            registerActivity(null);
            registerFragmentManager(null);
            registerLifecycle(null);
        }
    }
    private static void registerFragmentManager(@Nullable FragmentManager fm) { _oFragmentManager = fm;}
    private static void registerLifecycle(@Nullable Lifecycle lc) {_oLifecycle = lc;}
    public static void registerActivity(@Nullable Activity act) { _oActivity = act; registerContext(act); }
    public static void registerContext(@Nullable Context cnt) { _oContext = cnt; registerResources(_oContext != null ? _oContext.getResources() : null); }
    private static void registerResources(@Nullable Resources rsr) { _oResources = rsr;}

    @Nullable
    public static <T extends Activity> T getActivity() { return ObjectUtils.cast(_oActivity); }
    @Nullable
    public static Context getContext() {return _oContext;}
    @Nullable
    public static Resources getResources() { return _oResources; }
    @Nullable
    public static FragmentManager getFragmentManager() { return _oFragmentManager; }
    @Nullable
    public static Lifecycle getLifecycle() { return _oLifecycle; }

    @NonNull
    public static int newTransientID()
    {
        synchronized (_oLock)
        {
            return ++_iTransientID;
        }
    }
}