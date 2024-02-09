package it.pietroterracciano.kudos.Utils;

import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public abstract class ObjectUtils
{
    @Nullable
    public static <T> T cast(@Nullable Object o)
    {
        if (o != null)
            try { return (T) o; } catch (Exception ignored) {}

        return null;
    }
}