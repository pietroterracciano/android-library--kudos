package it.pietroterracciano.kudos.Modules.ToastModule.Builders;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;

import it.pietroterracciano.kudos.Modules.ToastModule.Constants.CToastingClass;
import it.pietroterracciano.kudos.Modules.ToastModule.Descriptors.ToastingDescriptor;
import it.pietroterracciano.kudos.Modules.ToastModule.Enums.EToastDuration;
import it.pietroterracciano.kudos.Modules.ToastModule.Toasting;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;
import it.pietroterracciano.kudos.Utils.LayoutInflaterUtils;

public final class ToastingBuilder
{
    @NonNull
    private static final Constructor<ToastingDescriptor>
        __cnsToastingDescriptor;
    @NonNull
    private static final Constructor<Toasting>
        __cnsToasting;

    static
    {
        __cnsToastingDescriptor = ConstructorUtils.getDeclared(CToastingClass.ToastingDescriptor);
        __cnsToasting = ConstructorUtils.getDeclared(CToastingClass.Toasting, CToastingClass.ToastingDescriptor);
    }

    @NonNull
    private final ToastingDescriptor _td;

    @NonNull
    public final ToastingBuilder setContext(@Nullable Context cnt) { _td.Context = cnt; return this; }
    @NonNull
    public final ToastingBuilder setView(@NonNull @LayoutRes int i) { setView(LayoutInflaterUtils.inflate(i)); return this; }
    @NonNull
    public final ToastingBuilder setView(@Nullable View v) { _td.View = v; return this; }
    @NonNull
    public final ToastingBuilder setText(@Nullable String s) { _td.Text = s; return this; }
    @NonNull
    public final ToastingBuilder setDuration(@Nullable EToastDuration e) { _td.Duration = e; return this; }

    private ToastingBuilder() { _td = ConstructorUtils.createInstance(__cnsToastingDescriptor); }

    @NonNull
    public Toasting build() { return ConstructorUtils.createInstance(__cnsToasting, _td.clone()); }
}
