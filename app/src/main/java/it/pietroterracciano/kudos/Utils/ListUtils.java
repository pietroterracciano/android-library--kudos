package it.pietroterracciano.kudos.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

public abstract class ListUtils
{
    @NonNull
    public static <ObjectType> int indexOf(@Nullable List<ObjectType> l, @Nullable ObjectType o)
    {
        return indexLastIndexOf(l, o, false);
    }
    @NonNull
    public static <ObjectType> int lastIndexOf(@Nullable List<ObjectType> l, @Nullable ObjectType o)
    {
        return indexLastIndexOf(l, o, true);
    }
    @NonNull
    private static <ObjectType> int indexLastIndexOf
    (
        @Nullable List<ObjectType> l,
        @Nullable ObjectType o,
        @NonNull boolean bLastIndexOf
    )
    {
        if(l == null)
            return -2;

        int j = -1;

        for(int i=0; i<l.size(); i++)
        {
            if(!Objects.equals(l.get(i), o))
                continue;

            j = i;

            if(!bLastIndexOf)
                break;
        }

        return j;
    }

    @NonNull
    public static <ObjectType> boolean add(@Nullable List<ObjectType> l, @Nullable ObjectType o)
    {
        if(l == null)
            return false;

        l.add(o);
        return true;
    }

    @NonNull
    public static <ObjectType> boolean set(@Nullable List<ObjectType> l, @NonNull int i, @Nullable ObjectType o)
    {
        if(!isValidIndex(l, i))
            return false;

        l.set(i, o);
        return true;
    }

    @NonNull
    public static <ObjectType> int adse(@Nullable List<ObjectType> l, @Nullable ObjectType o)
    {
        int i = indexOf(l, o);

        if(i < 0)
            l.add(o);
        else
            l.set(i, o);

        return i;
    }

    @Nullable
    public static <ObjectType> ObjectType get(@Nullable List<ObjectType> l, @NonNull int i)
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
