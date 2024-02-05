package it.pietroterracciano.kudos.Utils;

import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;

public abstract class ObjectUtils
{
    @Nullable
    public static <T> T cast(@Nullable Object o)
    {
        if (o != null)
            try { return (T) o; } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static <T> Constructor<T> getDeclaredConstructor(Class<T> cls, Class<?>... a)
    {
        if(cls != null)
            try { return cls.getDeclaredConstructor(a); } catch (Exception ignored) {}

        return null;
    }

    public static <T> T newInstance(Constructor<T> cns, Object... a)
    {
        if(cns != null)
            try { return cns.newInstance(a); } catch (Exception ignored) {}

        return null;
    }
}
