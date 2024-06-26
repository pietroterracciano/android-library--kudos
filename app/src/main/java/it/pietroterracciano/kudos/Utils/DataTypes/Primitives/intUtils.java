package it.pietroterracciano.kudos.Utils.DataTypes.Primitives;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.concurrent.ThreadLocalRandom;

import it.pietroterracciano.kudos.Utils.DataTypes.NonPrimitives.IntegerUtils;

public abstract class intUtils
{
    @NonNull
    public static int random()
    {
        return random(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    @NonNull
    public static int random(@NonNull int i)
    {
        return random(-i, i);
    }
    @NonNull
    public static int random(@NonNull int iMin, @NonNull int iMax)
    {
        if(iMin > iMax)
        {
            int i = iMin;
            iMin = iMax;
            iMax = i;
        }

        return ThreadLocalRandom.current().nextInt(iMin, iMax + 1);
    }
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