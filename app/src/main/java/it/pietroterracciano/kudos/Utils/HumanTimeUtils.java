package it.pietroterracciano.kudos.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;

public abstract class HumanTimeUtils
{
    @NonNull
    private static final double
        __dMilliSecondsDivisor = 1d,
        __dSecondsDivisor = __dMilliSecondsDivisor * 1000d,
        __dMinutesDivisor = __dSecondsDivisor * 60d,
        __dHoursDivisor = __dMinutesDivisor * 60d,
        __dDaysDivisor = __dHoursDivisor * 24d;

    @Nullable
    public static String from(Date oDate)
    {
        if(oDate == null)
            return null;

        Date
            dtCurrent = new Date();

        double
            dDifference =  dtCurrent.getTime() - oDate.getTime();

        int
            iOutput = (int) Math.floor( dDifference / __dDaysDivisor );

        if(iOutput > 0)
            return iOutput + " giorni fa";

        iOutput = (int) Math.floor( dDifference / __dHoursDivisor );

        if(iOutput > 0)
            return iOutput + " ora fa";

        iOutput = (int) Math.floor( dDifference / __dMinutesDivisor );

        if(iOutput > 0)
            return iOutput + " minuti fa";

        iOutput = (int) Math.floor( dDifference / __dSecondsDivisor );

        if(iOutput > 0)
            return iOutput + " secondi fa";

        else
            return "Adesso";
    }
}
