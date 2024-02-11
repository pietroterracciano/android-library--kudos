package it.pietroterracciano.kudos.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Field;

public abstract class FieldUtils
{
    @Nullable
    public static Field get(@Nullable Class<?> cls, @Nullable String s)
    {
        if(cls != null && s != null)
        {
            Field fld;
            try { fld = cls.getField(s); fld.setAccessible(true); } catch (Exception ignored) {}
        }

        return null;
    }

    @NonNull
    public static boolean set(@Nullable Object o, @Nullable Field fld, @Nullable Object v)
    {
        if(o != null && fld != null)
            try { fld.set(o, v); return true; } catch (Exception ignored) {}

        return false;
    }
}