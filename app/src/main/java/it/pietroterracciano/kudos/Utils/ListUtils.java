package it.pietroterracciano.kudos.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

public abstract class ListUtils
{
    @NonNull
    public static <T> int indexOf(@Nullable List<T> l, @Nullable T o)
    {
        return indexLastIndexOf(l, o, false);
    }
    @NonNull
    public static <T> int lastIndexOf(@Nullable List<T> l, @Nullable T o)
    {
        return indexLastIndexOf(l, o, true);
    }
    @NonNull
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
    }

    @NonNull
    public static <T> boolean add(@Nullable List<T> l, @Nullable T o)
    {
        if(l == null)
            return false;

        l.add(o);
        return true;
    }

    @NonNull
    public static <T> boolean set(@Nullable List<T> l, @NonNull int i, @Nullable T o)
    {
        if(!isValidIndex(l, i))
            return false;

        l.set(i, o);
        return true;
    }

    @NonNull
    public static <T> int adse(@Nullable List<T> l, @Nullable T o)
    {
        int i = indexOf(l, o);

        if(i < 0)
            l.add(o);
        else
            l.set(i, o);

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
    public static boolean remove(@Nullable List l, @NonNull int i)
    {
        if(!isValidIndex(l, i))
            return false;

        l.remove(i);
        return true;
    }

    @NonNull
    public static boolean isValidIndex(@Nullable List l, @NonNull int i)
    {
        return
            l != null
            && i > -1
            && i < l.size();
    }
}
