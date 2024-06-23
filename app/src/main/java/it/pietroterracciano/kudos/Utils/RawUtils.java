package it.pietroterracciano.kudos.Utils;

import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;

import java.io.InputStream;

public final class RawUtils
{
    @Nullable
    public static InputStream openFromResources(@RawRes @NonNull int i)
    {
        return ResourcesUtils.openRaw(i);
    }
    @Nullable
    public static InputStream openFromResources(Resources r, @RawRes @NonNull int i)
    {
        return ResourcesUtils.openRaw(r, i);
    }
}