package it.pietroterracciano.kudos.DataTypes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;

import it.pietroterracciano.kudos.Utils.DataTypes.Primitives.longUtils;
import it.pietroterracciano.kudos.Utils.DateUtils;

public class UnixTimeStamp implements Comparable<UnixTimeStamp>
{
    @NonNull
    private long _l;

    public UnixTimeStamp() { this(0L); }
    public UnixTimeStamp(@NonNull int i) { this(longUtils.convert(i)); }
    public UnixTimeStamp(@NonNull long l) { _l = l; }
    public UnixTimeStamp(@Nullable Long l) { this(longUtils.convert(l)); }
    public UnixTimeStamp(@Nullable Date dt) { this(longUtils.convert(dt));}

    @NonNull
    public Date convertToDate() { return DateUtils.convert(_l); }
    @NonNull
    public long convertToLong() { return _l; }

    public UnixTimeStamp addMilliSeconds(@NonNull int i) { return new UnixTimeStamp(_l + i); }
    public UnixTimeStamp addSeconds(@NonNull int i) { return addMilliSeconds(i * 1000); }
    public UnixTimeStamp addMinutes(@NonNull int i) { return addSeconds(i * 60); }
    public UnixTimeStamp addHours(@NonNull int i) { return addMinutes(i * 60); }
    public UnixTimeStamp addDays(@NonNull int i) { return addMinutes(i * 24); }
    public UnixTimeStamp addYears(@NonNull int i) { return addDays(i * 365); }

    public static UnixTimeStamp getCurrent() { return new UnixTimeStamp(System.currentTimeMillis()); }
    public static UnixTimeStamp getOrigin() { return new UnixTimeStamp(); }

    @Nullable
    public UnixTimeStamp getDifference(@Nullable UnixTimeStamp uts)
    {
        return uts != null
            ? new UnixTimeStamp(uts._l - _l)
            : null;
    }

    @Override
    public int compareTo(UnixTimeStamp uts)
    {
        if(uts == null || _l > uts._l) return 1;
        else if(_l == uts._l) return 0;
        else return -1;
    }
}