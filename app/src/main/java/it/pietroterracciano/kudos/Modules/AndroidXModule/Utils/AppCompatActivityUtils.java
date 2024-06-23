package it.pietroterracciano.kudos.Modules.AndroidXModule.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Utils.ActivityUtils;

public abstract class AppCompatActivityUtils extends ActivityUtils
{
    @NonNull
    public static boolean setSupportActionBar(@Nullable AppCompatActivity aca, @NonNull @IdRes int i)
    {
        return setSupportActionBar(aca, findViewById(aca,i));
    }
    @NonNull
    public static boolean setSupportActionBar(@Nullable AppCompatActivity aca, @Nullable Toolbar tlb)
    {
        if(aca != null && tlb != null)
            try { aca.setSupportActionBar(tlb); return true; } catch (Exception ignored) {}

        return false;
    }

    @Nullable
    public static ActionBar getSupportActionBar(@Nullable AppCompatActivity aca)
    {
        return aca != null ? aca.getSupportActionBar() : null;
    }

    @Nullable
    public static Integer start(Class<? extends Activity> oClass)
    {
        return start(Kudos.getActivity(), oClass);
    }
    @Nullable
    public static Integer start(Activity act, Class<? extends Activity> cls)
    {
        return
                act != null
            ?
                start(
                    act,
                    new Intent(act, cls)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)
                )
            :
                null;
    }
    @Nullable
    public static Integer start(Intent intt)
    {
        return start(Kudos.getActivity(), intt, null);
    }
    @Nullable
    public static Integer start(Activity act, Intent intt)
    {
        return start(act, intt, null);
    }
    @Nullable
    public static Integer start(Intent intt, Bundle bnd)
    {
        return start(Kudos.getActivity(), intt, bnd);
    }
    @Nullable
    public static Integer start(Activity act, Intent intt, Bundle bnd)
    {
        if(act != null && intt != null)
            try
            {
                if(bnd != null)
                    act.startActivity(intt, bnd);
                else
                    act.startActivity(intt);
            }
            catch (Exception ignored)
            {
            }

        return null;
    }
}
