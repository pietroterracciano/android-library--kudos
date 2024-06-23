package it.pietroterracciano.kudos.Utils;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Kudos;

public abstract class ActivityUtils
{
    @Nullable
    public static <T extends View> T findViewById(@Nullable Activity act, @NonNull @IdRes int i)
    {
        if(act != null)
            try { return act.findViewById(i); } catch (Exception ignored) {}

        return null;
    }

    @NonNull
    public static boolean setActionBar(@Nullable Activity act, @NonNull @IdRes int i)
    {
        return setActionBar(act, findViewById(act, i));
    }
    @NonNull
    public static boolean setActionBar(@Nullable Activity act, @Nullable Toolbar tlb)
    {
        if(act != null && tlb != null)
            try { act.setActionBar(tlb); return true; } catch (Exception ignored) {}

        return false;
    }

    @Nullable
    public static ActionBar getActionBar(@Nullable Activity act)
    {
        return act != null
            ? act.getActionBar()
            : null;
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
