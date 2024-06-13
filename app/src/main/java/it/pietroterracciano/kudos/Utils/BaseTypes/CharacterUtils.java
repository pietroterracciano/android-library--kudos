package it.pietroterracciano.kudos.Utils.BaseTypes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class CharacterUtils
{
    @Nullable
    public static Character convert(@Nullable Character c) { return c; }
    @Nullable
    public static Character convert(@NonNull char c) { return c; }
    @Nullable
    public static Character convert(@Nullable Integer i) { return i != null ? convert(i.intValue()) : null; }
    @Nullable
    public static Character convert(@NonNull int i) { try { return (char)(i + '0'); } catch (Exception ignored) {} return null; }
}
