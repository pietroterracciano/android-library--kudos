package it.pietroterracciano.kudos.Configs;

import androidx.annotation.NonNull;

public final class EventsConfigs
{
    @NonNull
    public static FacebookEventsConfigs FacebookEvents;

    public EventsConfigs()
    {
        FacebookEvents = new FacebookEventsConfigs();
    }

}
