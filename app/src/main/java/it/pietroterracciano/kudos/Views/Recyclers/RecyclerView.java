package it.pietroterracciano.kudos.Views.Recyclers;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;

import it.pietroterracciano.kudos.Behaviors.RecyclerViewBehavior;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;

public final class RecyclerView extends androidx.recyclerview.widget.RecyclerView
{
    @NonNull
    public final RecyclerViewBehavior Behavior;

    public RecyclerView(@NonNull Context context)
    {
        super(context);
        Behavior =RecyclerViewBehavior.from(this);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        Behavior =RecyclerViewBehavior.from(this);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        Behavior =RecyclerViewBehavior.from(this);
    }
}
