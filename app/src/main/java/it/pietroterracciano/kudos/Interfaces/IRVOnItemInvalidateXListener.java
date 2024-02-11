package it.pietroterracciano.kudos.Interfaces;

import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

public interface IRVOnItemInvalidateXListener<ItemType>
{
    void invalidateX(@NonNull View v, @NonNull ItemType itm);
}
