package it.pietroterracciano.kudos.Utils.TypesUtils.NumericUtils;

import androidx.annotation.Nullable;

public abstract class IntegerUtils
{
    @Nullable
    public static Integer from(@Nullable Float f) { return f != null ? f.intValue() : null; }
}