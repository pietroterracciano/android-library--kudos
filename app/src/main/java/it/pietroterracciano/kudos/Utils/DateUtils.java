package it.pietroterracciano.kudos.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;

public final class DateUtils
{
    @Nullable
    public static final Date convert(Long l) { return l != null ? new Date(l) : null; }
    @NonNull
    public static final Date convert(long l) { return new Date(l); }
}
