package it.pietroterracciano.kudos.Models;

import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Enums.ETEAction;

public class TouchEventModel
{
    public final ETEAction Action;
    public final int TapsCount;
    public final MotionEventModel MotionEvent0, MotionEvent1;

    private TouchEventModel(@Nullable MotionEventModel mem0, @Nullable MotionEventModel mem1, @Nullable ETEAction etea, @NonNull int i)
    {
        MotionEvent0 = mem0;
        MotionEvent1 = mem1;
        TapsCount = i;
        Action = etea != null ? etea : ETEAction.Unknown;
    }
}