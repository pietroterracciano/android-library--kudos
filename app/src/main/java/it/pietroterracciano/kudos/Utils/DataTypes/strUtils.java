package it.pietroterracciano.kudos.Utils.DataTypes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Constants.CString;

public abstract class strUtils
{
    @NonNull
    public static String convert(@Nullable String s) { return s != null ? s : CString.Empty; }
    @NonNull
    public static String convert(@Nullable Character c) { return convert(StringUtils.convert(c)); }
    @NonNull
    public static String convert(@Nullable char c) { return convert(StringUtils.convert(c)); }
    @NonNull
    public static String convert(@Nullable Integer i) { return convert(StringUtils.convert(i)); }
    @NonNull
    public static String convert(@Nullable int i) { return convert(StringUtils.convert(i)); }
    @NonNull
    public static String convert(@Nullable Float f) { return convert(StringUtils.convert(f)); }
    @NonNull
    public static String convert(@Nullable float f) { return convert(StringUtils.convert(f)); }
    @NonNull
    public static String convert(@Nullable Double d) { return convert(StringUtils.convert(d)); }
    @NonNull
    public static String convert(@Nullable double d) { return convert(StringUtils.convert(d)); }
}