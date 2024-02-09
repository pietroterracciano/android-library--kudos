package it.pietroterracciano.kudos.Utils.TypesUtils.NumericUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class intUtils
{
    @NonNull
    public static int from(Float f) { return from(IntegerUtils.from(f)); }
    @NonNull
    public static int from(@NonNull float f) { return (int)f; }
    @NonNull
    public static int from(@Nullable Integer i) { return i != null ? i : 0; }
}
