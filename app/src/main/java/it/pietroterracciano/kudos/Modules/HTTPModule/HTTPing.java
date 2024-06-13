package it.pietroterracciano.kudos.Modules.HTTPModule;

import androidx.annotation.NonNull;

import java.lang.reflect.Constructor;

import it.pietroterracciano.kudos.Modules.HTTPModule.Executors.HTTPingRequestExecutor;
import it.pietroterracciano.kudos.Modules.HTTPModule.Payloads.AHTTPingPayload;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;

public final class HTTPing
{
    @NonNull
    private static final Constructor<HTTPingRequestExecutor>
        _chttpingre = ConstructorUtils.getDeclared(HTTPingRequestExecutor.class);

    @NonNull
    public static HTTPingRequestExecutor requireRequestExecutor()
    {
        return ConstructorUtils.newInstance(_chttpingre);
    }
}
