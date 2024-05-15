package it.pietroterracciano.kudos.Modules.ThumbnailModule.Handlers;

import androidx.annotation.NonNull;

import java.lang.reflect.Constructor;

import it.pietroterracciano.kudos.Utils.ConstructorUtils;

public abstract class ThumbnailChainer
{
    private static final Constructor<ThumbnailChain>
        _cnsBuilder = ConstructorUtils.getDeclared(ThumbnailChain.class);

    @NonNull
    public static ThumbnailChain newChain()
    {
        return ConstructorUtils.newInstance(_cnsBuilder);
    }
}
