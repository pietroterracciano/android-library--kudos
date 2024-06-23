package it.pietroterracciano.kudos.Modules.HermesModule.Broadcasts;

import android.content.Intent;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import it.pietroterracciano.kudos.Utils.ThreadUtils;
import it.pietroterracciano.kudos.Modules.HermesModule.Listeners.IHermesInterBroadcastPacketListener;

public final class HermesInterBroadcast
{
    private final List<IHermesInterBroadcastPacketListener> _l;

    public HermesInterBroadcast()
    {
        _l = new ArrayList<>();
    }

    public HermesInterBroadcast registerPacketListener(@Nullable IHermesInterBroadcastPacketListener lst)
    {
        if(lst != null)
            synchronized (_l)
            {
                _l.add(lst);
            }

        return this;
    }

    public HermesInterBroadcast unregisterPacketListener(@Nullable IHermesInterBroadcastPacketListener lst)
    {
        if(lst != null)
            synchronized (_l)
            {
                _l.remove(lst);
            }

        return this;
    }

    public HermesInterBroadcast sendPacket(@Nullable Intent ntt)
    {
        synchronized (_l)
        {
            int j = _l.size();
            for(int i=0; i<j; i++) _l.get(i).onPacketReceive(ntt);
        }

        return this;
    }

    public HermesInterBroadcast sendPacketAsync(@Nullable Intent ntt)
    {
        ThreadUtils.runOnBackground(new Runnable() {
            @Override
            public void run()
            {
                sendPacket(ntt);
            }
        });
        return this;
    }
}
