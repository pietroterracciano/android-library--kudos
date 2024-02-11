package it.pietroterracciano.kudos.Controllers;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Controllers.ServiceController;
import it.pietroterracciano.kudos.Kudos;

public abstract class LayoutController
{
    @Nullable
    public static LayoutInflater getService()
    {
        return getService(Kudos.getContext());
    }

    @Nullable
    public static LayoutInflater getService(@Nullable Context cnt)
    {
        return ServiceController.getFromSystem(cnt, Context.LAYOUT_INFLATER_SERVICE);
    }

    @Nullable
    public static View inflate(@NonNull @LayoutRes int iResourceID, @Nullable ViewGroup vg, boolean bAttach2vg)
    {
        return inflate(Kudos.getContext(), iResourceID, vg, bAttach2vg);
    }
    @Nullable
    public static View inflate(@Nullable Context cnt, @NonNull @LayoutRes int iResourceID, @Nullable ViewGroup vg, boolean bAttach2vg)
    {
        LayoutInflater srv = getService(cnt);

        if(srv != null)
            try
            {
                return srv.inflate(iResourceID, vg, bAttach2vg);
            }
            catch (Exception ignored)
            {

            }

        return null;
    }
}
