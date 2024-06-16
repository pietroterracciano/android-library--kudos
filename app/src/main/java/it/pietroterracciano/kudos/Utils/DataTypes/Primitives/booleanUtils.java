package it.pietroterracciano.kudos.Utils.DataTypes.Primitives;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class booleanUtils
{
    @NonNull
    public static final boolean convert(@Nullable Boolean b) { return b != null ? b : false; }
    @NonNull
    public static final boolean convert(@NonNull boolean b) { return b; }
}
