package it.pietroterracciano.kudos.Constants;

import androidx.annotation.NonNull;

public abstract class CTime
{
    @NonNull
    public static final int
        MilliSecondsDivisor = 1,
        SecondsDivisor = MilliSecondsDivisor * 1000,
        MinutesDivisor = SecondsDivisor * 60,
        HoursDivisor = MinutesDivisor * 60,
        DaysDivisor = HoursDivisor * 24;
}