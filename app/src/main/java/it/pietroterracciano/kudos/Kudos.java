package it.pietroterracciano.kudos;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

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
    @Nullable
    private static DisplayMetrics _oDisplayMetrics;
    @Nullable
    private static Resources.Theme _oTheme;

    public static void registerActivity(@Nullable AppCompatActivity act)
    {
        if(act != null)
        {
            registerActivity((Activity) act);
            _registerFragmentManager(act.getSupportFragmentManager());
            _registerLifecycle(act.getLifecycle());
        }
        else
        {
            registerActivity(null);
            _registerFragmentManager(null);
            _registerLifecycle(null);
        }
    }

    public static void registerActivity(@Nullable Activity act)
    {
        _oActivity = act; registerContext(act);
        if(act != null)
            _registerTheme(act.getTheme());
        else
            _registerTheme(null);
    }
    public static void registerContext(@Nullable Context cnt)
    {
        _oContext = cnt;
        if(_oContext != null)
            _registerResources(_oContext.getResources());
        else
            _registerResources(null);
    }

    private static void _registerFragmentManager(@Nullable FragmentManager fm) { _oFragmentManager = fm;}
    private static void _registerLifecycle(@Nullable Lifecycle lc) {_oLifecycle = lc;}
    private static void _registerResources(@Nullable Resources rsr) { _oResources = rsr; _registerDisplayMetrics(_oResources != null ? _oResources.getDisplayMetrics() : null);}
    private static void _registerDisplayMetrics(@Nullable DisplayMetrics dm) { _oDisplayMetrics = dm;}
    private static void _registerTheme(@Nullable Resources.Theme thm) { _oTheme = thm; }
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
    @Nullable
    public static DisplayMetrics getDisplayMetrics() { return _oDisplayMetrics; }
    @Nullable
    public static Resources.Theme getTheme() { return _oTheme; }

    @NonNull
    public static int newTransientID()
    {
        synchronized (_oLock)
        {
            return ++_iTransientID;
        }
    }
}