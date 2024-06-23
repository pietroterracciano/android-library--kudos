package it.pietroterracciano.kudos.Modules.HermesModule.Listeners;

import android.content.Intent;

import androidx.annotation.Nullable;

public interface IHermesInterBroadcastPacketListener
{
    public void onPacketReceive(@Nullable Intent ntt);
}
