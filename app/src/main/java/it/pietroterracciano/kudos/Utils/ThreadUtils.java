package it.pietroterracciano.kudos.Utils;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.HashSet;

import it.pietroterracciano.kudos.Kudos;

public final class ThreadUtils
{
    @NonNull
    private static final HashSet<Integer>
        __hsTransientIDs = new HashSet<>(),
        __hsInterruptRequestedForTransientIDs = new HashSet<>();
    @NonNull
    private static final HashMap<Integer, Thread>
        __hmTransientIDs2Objects = new HashMap<>();
    @NonNull
    private static final HashMap<Thread, Integer>
        __hmObjects2TransientIDs = new HashMap<>();
    @NonNull
    private static final Object
        __oLock = new Object();

    private static void add2Hashes
    (
        @NonNull Thread oThread,
        @NonNull int iID
    )
    {
        synchronized (__oLock)
        {
            __hsTransientIDs.add(iID);
        }
        __hmTransientIDs2Objects.put(iID, oThread);
        __hmObjects2TransientIDs.put(oThread, iID);
        __hsInterruptRequestedForTransientIDs.remove(iID);
    }
    private static void removeFromHashes
    (
        @NonNull Thread oThread,
        @NonNull int iID
    )
    {
        synchronized (__oLock)
        {
            __hsTransientIDs.remove(iID);
        }
        __hmTransientIDs2Objects.remove(iID, oThread);
        __hmObjects2TransientIDs.remove(oThread, iID);
        __hsInterruptRequestedForTransientIDs.remove(iID);
    }

    @NonNull
    public static boolean sleep(int iMilliSeconds)
    {
        if(iMilliSeconds >  0)
            try
            {
                Thread.sleep(iMilliSeconds);
                return true;
            }
            catch (Exception ignored)
            {

            }

        return false;
    }

    public static void runOnForeground(@Nullable Runnable oRunnable)
    {
        runOnForeground(Kudos.getActivity(), oRunnable);
    }
    public static void runOnForeground
    (
        @Nullable Activity oActivity,
        @Nullable Runnable oRunnable
    )
    {
        if(oActivity == null)
            return;

        oActivity.runOnUiThread(
            new Runnable()
            {
                @Override
                public void run()
                {
                    RunnableUtils.run(oRunnable);
                }
            }
        );
    }

    @NonNull
    public static int runOnBackground(@Nullable Runnable oRunnable)
    {
        int
            iTransientID = Kudos.newTransientID();

        Thread
            o  = new Thread
            (
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Thread o = Thread.currentThread();
                        add2Hashes(o, iTransientID);
                        RunnableUtils.run(oRunnable);
                        removeFromHashes(o, iTransientID);
                    }
                }
            );

        o.start();

        return iTransientID;
    }

    @Nullable
    public static Integer getTransientID(@Nullable Thread o)
    {
        return __hmObjects2TransientIDs.get(o);
    }

    @Nullable
    public static Thread get(@NonNull int iTransientID)
    {
        return __hmTransientIDs2Objects.get(iTransientID);
    }

    @NonNull
    public static boolean isRunning(@NonNull int iTransientID)
    {
        return __hsTransientIDs.contains(iTransientID);
    }

    @NonNull
    public static boolean isInterruptRequested(@Nullable Thread o)
    {
        Integer iTransientID = getTransientID(o);

        return
            iTransientID != null
            && isInterruptRequested(iTransientID);
    }
    @NonNull
    public static boolean isInterruptRequested(@NonNull int iTransientID)
    {
        return __hsInterruptRequestedForTransientIDs.contains(iTransientID);
    }

    public static void interrupt()
    {
        synchronized (__oLock)
        {
            for(Integer iTransientID : __hsTransientIDs)
                interrupt(iTransientID);
        }
    }

    @NonNull
    public static boolean interrupt(@NonNull int iTransientID)
    {
        return interrupt(get(iTransientID));
    }
    @NonNull
    public static boolean interrupt(@Nullable Thread o)
    {
        if(
            o == null
            || !o.isAlive()
        )
            return false;

        Integer
            iTransientID = getTransientID(o);

        if(iTransientID != null)
            __hsInterruptRequestedForTransientIDs.add(iTransientID);

        try
        {
            o.interrupt();
        }
        catch (Exception oException)
        {
            return false;
        }

        return !o.isAlive();
    }
}
