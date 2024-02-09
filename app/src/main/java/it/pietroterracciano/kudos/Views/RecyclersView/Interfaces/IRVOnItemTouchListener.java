package it.pietroterracciano.kudos.Views.RecyclersView.Interfaces;

import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface IRVOnItemTouchListener extends IRVOnItemEventListener
{
    @NonNull
    boolean onTouch(@NonNull MotionEvent oMotionEvent, @Nullable Object o);
}