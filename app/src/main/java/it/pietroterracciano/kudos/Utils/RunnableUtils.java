package it.pietroterracciano.kudos.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class RunnableUtils
{
    @NonNull
    public static boolean run(@Nullable Runnable o)
    {
        if(o != null)
            try { o.run(); return true; }
            catch (Exception ignored) {}

        return false;
    }
}
