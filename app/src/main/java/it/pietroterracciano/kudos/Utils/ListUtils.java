package it.pietroterracciano.kudos.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class ListUtils
{
    @NonNull
    public static <T> int indexOf(@Nullable List<T> l, @Nullable T o)
    {
        return l != null ? l.indexOf(o) : -2; // indexLastIndexOf(l, o, false);
    }
    @NonNull
    public static <T> int lastIndexOf(@Nullable List<T> l, @Nullable T o)
    {
        return l != null ? l.lastIndexOf(o) : -2; //indexLastIndexOf(l, o, true);
    }

    @Nullable
    public static <T> List<T> from(@Nullable T... a)
    {
        if(a != null)
            try { return Arrays.asList(a); } catch (Exception ignored) {}
        return null;
    }

    /*@NonNull
    private static <T> int indexLastIndexOf
    (
        @Nullable List<T> l,
        @Nullable T o,
        @NonNull boolean bLastIndexOf
    )
    {
        if(l == null)
            return -2;

        int j = l.size(), k = -1;

        for(int i=0; i<j; i++)
        {
            if(!Objects.equals(l.get(i), o))
                continue;

            k = i;

            if(!bLastIndexOf)
                break;
        }

        return k;
    }*/

    @NonNull
    public static <T> int size(@Nullable List<T> l)
    {
        return l != null ? l.size() : -1;
    }

    @NonNull
    public static <T> boolean add(@Nullable List<T> l, @Nullable T o, @NonNull boolean bOnHead)
    {
        return add(l, bOnHead ? 0 : size(l), o);
    }
    @NonNull
    public static <T> boolean add(@Nullable List<T> l, @NonNull int i, @Nullable T o)
    {
        if(!isValidIndex(l, i, true)) return false;
        l.add(i, o); return true;
    }

    @NonNull
    public static <T> boolean set(@Nullable List<T> l, @NonNull int i, @Nullable T o)
    {
        if(!isValidIndex(l, i)) return false;
        l.set(i, o); return true;
    }

    @NonNull
    public static <T> int adse(@Nullable List<T> l, @Nullable T o, boolean bOnHead)
    {
        int i = indexOf(l, o);

        if(i < 0)
            add(l, o, bOnHead);
        else
            set(l, i, o);

        return i;
    }

    @Nullable
    public static <T> T get(@Nullable List<T> l, @NonNull int i)
    {
        return
            isValidIndex(l, i)
                ? l.get(i)
                : null;
    }

    @Nullable
    public static boolean remove(@Nullable List<?> l, @NonNull int i)
    {
        if(!isValidIndex(l, i)) return false;
        l.remove(i); return true;
    }

    @NonNull
    public static boolean isValidIndex(@Nullable List<?> l, @NonNull int i)
    {
        return isValidIndex(l, i, false);
    }

    @NonNull
    private static boolean isValidIndex(@Nullable List<?> l, @NonNull int i, @NonNull boolean bOnAdd)
    {
        return
            i > -1
            &&
            (
                (
                    !bOnAdd
                    && i < size(l)
                )
                ||
                (
                    bOnAdd
                    && i <= size(l)
                )
            );
    }
}
