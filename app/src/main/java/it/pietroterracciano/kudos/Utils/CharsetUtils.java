package it.pietroterracciano.kudos.Utils;

import androidx.annotation.Nullable;

import java.nio.charset.Charset;

public abstract class CharsetUtils
{
    @Nullable
    public static Charset get(@Nullable String s)
    {
        if(s != null)
            try { return Charset.forName(s); } catch (Exception ignored) {}

        return null;
    }
}
