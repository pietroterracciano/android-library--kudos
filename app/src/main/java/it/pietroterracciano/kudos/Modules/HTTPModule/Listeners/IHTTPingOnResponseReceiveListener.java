package it.pietroterracciano.kudos.Modules.HTTPModule.Listeners;

import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Modules.HTTPModule.Types.HTTPingResponse;

@FunctionalInterface
public interface IHTTPingOnResponseReceiveListener
{
    public void onResponseReceive(@Nullable HTTPingResponse httpingr);
}
