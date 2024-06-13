package it.pietroterracciano.kudos.Utils.BaseTypes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class charUtils
{
    @NonNull
    public static char convert(@Nullable Character c) { return c != null ? c : '\0'; };
    @NonNull
    public static char convert(@NonNull char c) { return c; }
    @NonNull
    public static char convert(@Nullable Integer i) { return convert(CharacterUtils.convert(i)); }
    @NonNull
    public static char convert(@NonNull int i) { return convert(CharacterUtils.convert(i)); }
}