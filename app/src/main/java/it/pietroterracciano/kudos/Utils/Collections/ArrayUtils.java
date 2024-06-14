package it.pietroterracciano.kudos.Utils.Collections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.pietroterracciano.kudos.Utils.ListUtils;

public abstract class ArrayUtils
{
    @Nullable
    public static <T> T get(@Nullable T[] a, @NonNull int i)
    {
        return
            isValidIndex(a, i)
                ? a[i]
                : null;
    }

    @NonNull
    public static boolean isValidIndex(@Nullable Object[] a, @NonNull int i)
    {
        return
            a != null
            && i > -1
            && i < a.length;
    }

    @Nullable
    public static <T> T[] createInstance(@Nullable Class<T> c, @NonNull int i)
    {
        if(c != null && i > -1)
            try { return (T[]) Array.newInstance(c, i); } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static <T> T[] from(@Nullable List<T> l, @Nullable Class<T> c)
    {
        return from(l, c, -1);
    }
    @Nullable
    public static <T> T[] from(@Nullable List<T> l, @Nullable Class<T> c, @NonNull int i)
    {
        if(l != null)
            try { return resize( l.toArray( createInstance(c, 0)), i); } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static <T> T[] resize(@Nullable T[] a, @NonNull int i)
    {
        if(a != null)
        {
            if(i < 0 || i > a.length)
                return a;
            else
                try { return Arrays.copyOf(a, i); } catch (Exception ignored) {}
        }

        return null;
    }

    @Nullable
    public static <T> T[] prepend(@Nullable Class<T> cls, @Nullable T[] a0, @Nullable T[] a1)
    {
        return append(cls, a1, a0);
    }
    @Nullable
    public static <T> T[] append(@Nullable Class<T> cls, @Nullable T[] a0, @Nullable T[] a1)
    {
        if
        (
            cls == null
            ||
            (
                a0 == null
                && a1 == null
            )
        )
            return null;
        else if
        (
            a0 != null
            && a1 == null
        )
            return a0;
        else if (a0 == null)
            return a1;

        T[]
            a = createInstance(cls, a0.length + a1.length);

        if(a == null)
            return null;

        System.arraycopy(
            a0,
            0,
            a,
            0,
            a0.length
        );

        System.arraycopy(
            a1,
            0,
            a,
            a0.length,
            a1.length
        );

        return a;
    }

    @Nullable
    public static <T> T[][] split(@Nullable Class<T> clso, @Nullable Class<T[]> clsa, @Nullable T[] a, @NonNull int i)
    {
        if (clso == null || clsa == null || !isValidIndex(a, i))
            return null;

        T[][]
            a0 = createInstance(clsa, 2);

        if(a0 == null)
            return null;

        a0[0] = createInstance(clso, i);
        a0[1] = createInstance(clso, a.length - i);

        if(a0[0] == null || a0[1] == null)
            return null;

        System.arraycopy(a, 0, a0[0], 0, a0[0].length);
        System.arraycopy(a, i, a0[1], 0, a0[1].length);

        return a0;
    }
}