package it.pietroterracciano.kudos.Controllers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Type;
import java.util.HashMap;

import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Utils.ObjectUtils;

public abstract class ServiceController
{
    @NonNull
    private static final Object _oLock = new Object();
    @NonNull
    private static final HashMap<String, Object> _hmNames2Objects = new HashMap<>();

    @Nullable
    public static <T> T getFromSystem(@Nullable String s)
    {
        return getFromSystem(Kudos.getContext(), s);
    }
    @Nullable
    public static <T> T getFromSystem(@Nullable Context cnt, @Nullable String s)
    {
        if(cnt == null || s == null || s.length() < 1)
            return null;

        synchronized (_oLock)
        {
            Object
                srv = _hmNames2Objects.get(s);

            if(srv == null)
                srv = cnt.getSystemService(s);

            T
                t = ObjectUtils.cast(srv);

            if(t != null)
                _hmNames2Objects.put(s, t);

            return t;
        }
    }
}
