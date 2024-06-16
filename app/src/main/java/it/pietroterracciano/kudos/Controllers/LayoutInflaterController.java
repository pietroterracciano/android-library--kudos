package it.pietroterracciano.kudos.Controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Constructor;

import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;
import it.pietroterracciano.kudos.Utils.Views.ViewUtils;

public abstract class LayoutInflaterController
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

    @Nullable
    public static <T extends Fragment> View inflate(@Nullable ViewGroup x_vg, Class<T> cls, boolean bAttach2vg)
    {
        if(x_vg == null || cls == null) return null;
        LayoutInflater li = getService();
        if(li == null) return null;
        Constructor<T> cns =  ConstructorUtils.getDeclared(cls);
        if(cns == null) return null;
        T t = ConstructorUtils.createInstance(cns);
        if(t == null) return null;
        View x_v = t.onCreateView(li, x_vg, null);
        if(x_v == null) return null;
        t.onViewCreated(x_v, null);
        if(!bAttach2vg) return x_v;
        ViewUtils.addChild(x_vg, x_v);
        return x_v;
    }
}
