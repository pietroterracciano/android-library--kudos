package it.pietroterracciano.kudos.Utils.Collections.Primitives;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class byteUtils
{
    @NonNull
    public static byte convert(@Nullable Byte bt) { return bt != null ? bt : 0; }
}
