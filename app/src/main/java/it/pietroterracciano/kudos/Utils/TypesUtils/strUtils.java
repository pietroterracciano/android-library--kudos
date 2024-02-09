package it.pietroterracciano.kudos.Utils.TypesUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Constants.CString;

public abstract class strUtils
{
    @NonNull
    public static String from(int i) { return String.valueOf(i); }
    @NonNull
    public static String from(@Nullable String s) { return s != null ? s : CString.Empty; }
}