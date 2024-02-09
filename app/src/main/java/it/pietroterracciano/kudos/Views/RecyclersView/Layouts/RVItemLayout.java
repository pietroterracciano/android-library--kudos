package it.pietroterracciano.kudos.Views.RecyclersView.Layouts;

import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import kotlin.Function;
import kotlin.jvm.functions.Function2;

public final class RVItemLayout<ItemType>
{
    @NonNull
    public Class<ItemType> Class;
    @NonNull @LayoutRes
    public int LayoutResourceID;
    @Nullable
    public BiConsumer<View, ItemType> OnItemInvalidateX;

    public RVItemLayout(@NonNull Class<ItemType> cls, @NonNull @LayoutRes int i, @Nullable BiConsumer<View, ItemType> cnm)
    {
        Class = cls;
        LayoutResourceID = i;
        OnItemInvalidateX = cnm;
    }
}
