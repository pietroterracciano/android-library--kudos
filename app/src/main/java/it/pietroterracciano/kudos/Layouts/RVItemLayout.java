package it.pietroterracciano.kudos.Layouts;

import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Interfaces.Action3;

public class RVItemLayout<ItemType>
{
    @NonNull
    public final Class<ItemType> ItemClass;
    @NonNull
    @LayoutRes
    public final int LayoutResourceID;
    @Nullable
    public final Action3<View, Integer, ItemType> OnInvalidateItemXListener;

    public RVItemLayout(@NonNull Class<ItemType> cls, @NonNull @LayoutRes int i)
    {
        this(cls, i, null);
    }
    public RVItemLayout(@NonNull Class<ItemType> cls, @NonNull @LayoutRes int i, @Nullable Action3<View, Integer, ItemType> lstOnInvalidateItemX)
    {
        ItemClass = cls;
        LayoutResourceID = i;
        OnInvalidateItemXListener = lstOnInvalidateItemX;
    }
}