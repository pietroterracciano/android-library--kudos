package it.pietroterracciano.kudos.Utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Utils.DataTypes.StringUtils;

public abstract class ServiceUtils
{
    @NonNull
    private static final HashMap<String, Object> __hm;

    static
    {
        __hm  = new HashMap<>();
    }

    @Nullable
    public static <T> T getFromSystem(@Nullable String s)
    {
        return getFromSystem(Kudos.getContext(), s);
    }
    @Nullable
    public static <T> T getFromSystem(@Nullable Context cnt, @Nullable String s)
    {
        if(cnt == null || StringUtils.isBlank(s))
            return null;

        synchronized (__hm)
        {
            Object
                srv = __hm.get(s);

            if(srv == null)
                srv = cnt.getSystemService(s);

            T
                t = ObjectUtils.cast(srv);

            if(t != null)
                __hm.put(s, t);

            return t;
        }
    }
}
