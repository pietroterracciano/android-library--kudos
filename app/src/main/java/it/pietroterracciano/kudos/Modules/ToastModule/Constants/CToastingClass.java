package it.pietroterracciano.kudos.Modules.ToastModule.Constants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import it.pietroterracciano.kudos.Modules.ToastModule.Builders.ToastingBuilder;
import it.pietroterracciano.kudos.Modules.ToastModule.Descriptors.ToastingDescriptor;
import it.pietroterracciano.kudos.Modules.ToastModule.Toasting;

public final class CToastingClass
{
    @NonNull
    public static final Class<Toasting>
        Toasting = it.pietroterracciano.kudos.Modules.ToastModule.Toasting.class;
    @NonNull
    public static final Class<ToastingBuilder>
        ToastingBuilder = it.pietroterracciano.kudos.Modules.ToastModule.Builders.ToastingBuilder.class;
    @NonNull
    public static final Class<ToastingDescriptor>
        ToastingDescriptor = it.pietroterracciano.kudos.Modules.ToastModule.Descriptors.ToastingDescriptor.class;
}
