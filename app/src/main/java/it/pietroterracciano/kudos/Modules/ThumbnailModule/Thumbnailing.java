package it.pietroterracciano.kudos.Modules.ThumbnailModule;

import androidx.annotation.NonNull;

import java.lang.reflect.Constructor;

import it.pietroterracciano.kudos.Modules.ThumbnailModule.Executors.ThumbnailingExecutor;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;

public abstract class Thumbnailing
{
    @NonNull
    private static final Constructor<ThumbnailingExecutor>
        _cnste = ConstructorUtils.getDeclared(ThumbnailingExecutor.class);

    @NonNull
    public static ThumbnailingExecutor requireExecutor()
    {
        return ConstructorUtils.newInstance(_cnste);
    }
}
