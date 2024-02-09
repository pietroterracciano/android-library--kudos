package it.pietroterracciano.kudos.Utils;

import androidx.annotation.Nullable;

import java.lang.reflect.Method;

public abstract class MethodUtils
{
    @Nullable
    public static Method getDeclared(@Nullable Class<?> cls, @Nullable String s, @Nullable Class<?>... a)
    {
        if(cls != null && s != null)
        {
            Method m;
            try { m = cls.getDeclaredMethod(s, a); m.setAccessible(true); return m; } catch (Exception ignored) {}
        }

        return null;
    }

    @Nullable
    public static <ObjectType> ObjectType invoke(@Nullable Object o, @Nullable Method m, @Nullable Object... a)
    {
        if(o != null && m != null)
            try { return ObjectUtils.cast(m.invoke(o, a)); } catch (Exception ignored) {}

        return null;
    }
}
