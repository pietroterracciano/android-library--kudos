package it.pietroterracciano.kudos.Utils.Collections.NonPrimitives;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Constants.CClass;
import it.pietroterracciano.kudos.Utils.Collections.ArrayUtils;

public final class BytesUtils
{
    @Nullable
    public static Byte[] convert(@Nullable byte[] ba)
    {
        if(ba == null) return null;
        Byte[] ba0 = new Byte[ba.length];
        for(int i=0; i<ba0.length; i++) ba0[i] = ba[i];
        return ba0;
    }

    @Nullable
    public static Byte[][] split(@Nullable byte[] ba, @NonNull int i) { return split(convert(ba), i); }
    @Nullable
    public static Byte[][] split(@Nullable Byte[] ba, @NonNull int i) { return ArrayUtils.split(CClass.Byte, CClass.BytesArray, ba, i); }
    @Nullable
    public static Byte[] prepend(@Nullable byte[] ba0, @Nullable Byte[] ba1) { return prepend(convert(ba0), ba1); }
    @Nullable
    public static Byte[] prepend(@Nullable Byte[] ba0, @Nullable byte[] ba1) { return prepend(ba0, convert(ba1)); }
    @Nullable
    public static Byte[] prepend(@Nullable byte[] ba0, @Nullable byte[] ba1) { return prepend(convert(ba0), convert(ba1)); }
    @Nullable
    public static Byte[] prepend(@Nullable Byte[] ba0, @Nullable Byte[] ba1) { return ArrayUtils.prepend(CClass.Byte, ba0, ba1); }
    @Nullable
    public static Byte[] append(@Nullable byte[] ba0, @Nullable Byte[] ba1) { return append(convert(ba0), ba1); }
    @Nullable
    public static Byte[] append(@Nullable Byte[] ba0, @Nullable byte[] ba1) { return append(ba0, convert(ba1)); }
    @Nullable
    public static Byte[] append(@Nullable byte[] ba0, @Nullable byte[] ba1) { return append(convert(ba0), convert(ba1)); }
    @Nullable
    public static Byte[] append(@Nullable Byte[] ba0, @Nullable Byte[] ba1) { return ArrayUtils.append(CClass.Byte, ba0, ba1); }
}