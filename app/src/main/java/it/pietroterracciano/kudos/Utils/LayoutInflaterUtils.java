package it.pietroterracciano.kudos.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Utils.Views.FragmentUtils;
import it.pietroterracciano.kudos.Utils.Views.ViewGroupUtils;

public final class LayoutInflaterUtils
{
    @Nullable
    public static LayoutInflater getService()
    {
        return getService(Kudos.getContext());
    }

    @Nullable
    public static LayoutInflater getService(@Nullable Context cnt)
    {
        return ServiceUtils.getFromSystem(cnt, Context.LAYOUT_INFLATER_SERVICE);
    }

    @Nullable
    public static View inflate(@NonNull @LayoutRes int i)
    {
        return inflate(i, null);
    }
    @Nullable
    public static View inflate(@NonNull @LayoutRes int i, @Nullable ViewGroup vg)
    {
        return inflate(i, vg, false);
    }
    @Nullable
    public static View inflate(@NonNull @LayoutRes int i, @Nullable ViewGroup vg, boolean bAttach2vg)
    {
        return inflate(Kudos.getContext(), i, vg, bAttach2vg);
    }
    public static View inflate(@Nullable Context cnt, @NonNull @LayoutRes int i)
    {
        return inflate(cnt, i, null);
    }
    public static View inflate(@Nullable Context cnt, @NonNull @LayoutRes int i, @Nullable ViewGroup vg)
    {
        return inflate(cnt, i, vg, false);
    }
    @Nullable
    public static View inflate(@Nullable Context cnt, @NonNull @LayoutRes int i, @Nullable ViewGroup vg, boolean bAttach2vg)
    {
        LayoutInflater srv = getService(cnt);

        if(srv != null)
            try
            {
                return srv.inflate(i, vg, bAttach2vg);
            }
            catch (Exception ignored)
            {

            }

        return null;
    }

    @Nullable
    public static <T extends Fragment> T inflate(@Nullable ViewGroup vg, Class<T> cls, boolean bAttach2vg)
    {
        if(vg == null) return null;
        LayoutInflater li = getService();
        if(li == null) return null;
        T t = FragmentUtils.createInstance(cls);
        if(t == null) return null;
        View v = t.onCreateView(li, vg, null);
        if(v == null) return t;
        t.onViewCreated(v, null);
        if(!bAttach2vg) return t;
        ViewGroupUtils.removeAllViews(vg);
        ViewGroupUtils.addView(vg, v);
        return t;
    }
}
