package it.pietroterracciano.kudos.Controllers;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.annotation.NonNull;

import it.pietroterracciano.kudos.Kudos;

public class VibrationController
{
    @NonNull
    public static boolean vibrate(@NonNull long l)
    {
        return vibrate(Kudos.getContext(), l);
    }

    public static boolean vibrate(Context cnt, @NonNull long l)
    {
        if(cnt == null || l < 1)
            return false;

        Vibrator
            vbr = ServiceController.getFromSystem(cnt, Context.VIBRATOR_SERVICE);

        if(vbr == null)
            return false;

        VibrationEffect
            ve;

        try
        {
            ve = VibrationEffect.createOneShot(l, VibrationEffect.DEFAULT_AMPLITUDE);
        }
        catch (Exception ignored)
        {
            ve = null;
        }

        if (ve != null)
            try { vbr.vibrate(ve); return true; } catch (Exception ignored){}
        else
            try { vbr.vibrate(l); return true; } catch (Exception ignored){}

        return false;
    }
}
