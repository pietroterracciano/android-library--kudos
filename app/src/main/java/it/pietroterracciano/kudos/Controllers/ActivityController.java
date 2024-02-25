package it.pietroterracciano.kudos.Controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Kudos;

public abstract class ActivityController
{
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
