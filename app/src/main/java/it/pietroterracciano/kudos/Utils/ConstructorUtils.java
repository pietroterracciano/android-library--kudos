package it.pietroterracciano.kudos.Utils;

import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public abstract class ConstructorUtils
{
    @Nullable
    public static <T> Constructor<T> getDeclared(@Nullable Class<T> cls, @Nullable Class<?>... a)
    {
        if(cls != null)
        {
            Constructor<T> c;
            try { c = cls.getDeclaredConstructor(a); c.setAccessible(true); return c;  } catch (Exception ignored) {}
        }

        return null;
    }

    @Nullable
    public static <T> T newInstance(@Nullable Constructor<T> cns, @Nullable Object... a)
    {
        if(cns != null)
            try { return cns.newInstance(a); } catch (Exception ignored) {}

        return null;
    }
}
