package it.pietroterracciano.kudos.Utils.DataTypes.NumericUtils.Primitives;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Utils.DataTypes.NumericUtils.NonPrimitives.IntegerUtils;

public abstract class integerUtils
{
    @NonNull
    public static int convert(@Nullable Integer i) { return i != null ? i : 0; }
    @NonNull
    public static int convert(@NonNull int i) { return i; }
    @NonNull
    public static int convert(@Nullable Float f) { return convert(IntegerUtils.convert(f)); }
    @NonNull
    public static int convert(@NonNull float f) { return convert(IntegerUtils.convert(f)); }
    @NonNull
    public static int convert(@Nullable Double d) { return convert(IntegerUtils.convert(d)); }
    @NonNull
    public static int convert(@NonNull double d) { return convert(IntegerUtils.convert(d)); }
    @NonNull
    public static int convert(@Nullable Character c) { return convert(IntegerUtils.convert(c)); }
    @NonNull
    public static int convert(@NonNull char c) { return convert(IntegerUtils.convert(c)); }
}