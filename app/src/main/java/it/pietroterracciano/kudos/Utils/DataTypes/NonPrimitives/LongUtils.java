package it.pietroterracciano.kudos.Utils.DataTypes.NonPrimitives;

import androidx.annotation.Nullable;

import java.util.Date;

public final class LongUtils
{
    @Nullable
    public static Long convert(@Nullable String s)
    {
        if(s != null)
            try { return Long.parseLong(s); } catch (Exception ignored) {}

        return null;
    }

    @Nullable
    public static Long convert(@Nullable Date dt) { return dt != null ? dt.getTime() : null; }
}
