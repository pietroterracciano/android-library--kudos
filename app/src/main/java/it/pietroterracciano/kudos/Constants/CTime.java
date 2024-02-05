package it.pietroterracciano.kudos.Constants;

public abstract class CTime
{
    public static final int
        MilliSecondsDivisor = 1,
        SecondsDivisor = MilliSecondsDivisor * 1000,
        MinutesDivisor = SecondsDivisor * 60,
        HoursDivisor = MinutesDivisor * 60,
        DaysDivisor = HoursDivisor * 24;
}
