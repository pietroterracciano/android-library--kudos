package it.pietroterracciano.kudos.Models;

import android.view.MotionEvent;

import androidx.annotation.NonNull;

public class MotionEventModel
{
    public final float X, Y;
    public final int PointerCount;
    public final int Action;
    public final long EventTime;

    private MotionEventModel(@NonNull MotionEvent me)
    {
        X = me.getX();
        Y = me.getY();
        PointerCount = me.getPointerCount();
        Action = me.getActionMasked();
        EventTime = me.getEventTime();
    }
}