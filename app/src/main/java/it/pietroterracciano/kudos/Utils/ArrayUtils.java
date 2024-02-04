package it.pietroterracciano.kudos.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public abstract class ArrayUtils
{
    @Nullable
    public static <ItemType> ItemType get(@Nullable ItemType[] a, @NonNull int i)
    {
        return
            isValidIndex(a, i)
                ? a[i]
                : null;
    }

    public static boolean isValidIndex(@Nullable Object[] a, @NonNull int i)
    {
        return
            a != null
            && i > -1
            && i < a.length;
    }

    @Nullable
    public static <ObjectType> ObjectType[] newInstance(@Nullable Class<ObjectType> c, @NonNull int i)
    {
        if(c != null && i > -1)
            try { return (ObjectType[]) Array.newInstance(c, i); } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static <ObjectType> ObjectType[] from(@Nullable List<ObjectType> l, @Nullable Class<ObjectType> c)
    {
        return from(l, c, -1);
    }
    @Nullable
    public static <ObjectType> ObjectType[] from(@Nullable List<ObjectType> l, @Nullable Class<ObjectType> c, @NonNull int i)
    {
        if(l != null)
            try { return resize( l.toArray( newInstance(c, 0)), i); } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static <ObjectType> ObjectType[] resize(@Nullable ObjectType[] a, @NonNull int i)
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

}