package it.pietroterracciano.kudos.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import it.pietroterracciano.kudos.Utils.Collections.ArrayUtils;

public abstract class ClassUtils
{
    @Nullable
    public static <T> Class<T> of(@Nullable T o)
    {
        return
            o != null
                ? ObjectUtils.cast(o.getClass())
                : null;
    }

    @Nullable
    public static <T> Class<T> of()
    {
        return ObjectUtils.cast(new TypeToken<T>(){}.getRawType());
    }

    @Nullable
    public static Type[] getActualTypeArguments(@Nullable Class<?> cls)
    {
        if(cls == null)
            return null;

        Type
            t = cls.getGenericSuperclass();

        if(!(t instanceof ParameterizedType))
            return null;

        ParameterizedType
            pt = (ParameterizedType)t;

        return pt.getActualTypeArguments();
    }
    @Nullable
    public static Type getActualTypeArgument(@Nullable Class<?> cls, @NonNull int i)
    {
        Type[] a = getActualTypeArguments(cls);
        return ArrayUtils.isValidIndex(a, i) ? a[i] : null;
    }
    @Nullable
    public static <T> Class<T> getActualClassArgument(@Nullable Class<?> cls, @NonNull int i)
    {
        return ObjectUtils.cast(getActualTypeArgument(cls, i));
    }
}