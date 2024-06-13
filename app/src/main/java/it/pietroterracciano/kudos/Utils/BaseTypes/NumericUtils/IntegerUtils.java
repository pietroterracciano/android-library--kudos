package it.pietroterracciano.kudos.Utils.BaseTypes.NumericUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class IntegerUtils
{
    @Nullable
    public static Integer convert(@Nullable Integer i) { return i; }
    @Nullable
    public static Integer convert(@NonNull int i) { return i; }
    @Nullable
    public static Integer convert(@Nullable Float f) { return f != null ? convert(f.floatValue()) : null; }
    @Nullable
    public static Integer convert(@NonNull float f) { try { return (int)f; } catch (Exception ignored) {return null;}}
    @Nullable
    public static Integer convert(@Nullable Double d) { return d != null ? convert(d.doubleValue()) : null; }
    @Nullable
    public static Integer convert(@NonNull double d) { try { return (int)d; } catch (Exception ignored) {return null;}}
    @Nullable
    public static Integer convert(@Nullable Character c) { return c != null ? convert(c.charValue()) : null; }
    @Nullable
    public static Integer convert(@NonNull char c) { try {return (int)c;} catch (Exception ignored) {return null;}}
}