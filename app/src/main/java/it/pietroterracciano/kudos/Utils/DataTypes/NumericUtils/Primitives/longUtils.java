package it.pietroterracciano.kudos.Utils.DataTypes.NumericUtils.Primitives;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;

import it.pietroterracciano.kudos.Utils.DataTypes.NumericUtils.NonPrimitives.LongUtils;

public final class longUtils
{
    @NonNull
    public static long convert(@Nullable String s) { return convert(LongUtils.convert(s)); }
    @NonNull
    public static long convert(@NonNull long l) {return l;}
    @NonNull
    public static long convert(@Nullable Long l) {return l != null ? l : 0L; };
    @NonNull
    public static long convert(@Nullable Date dt) { return convert(LongUtils.convert(dt)); }
}
