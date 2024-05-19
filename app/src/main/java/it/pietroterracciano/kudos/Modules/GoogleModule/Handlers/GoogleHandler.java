package it.pietroterracciano.kudos.Modules.GoogleModule.Handlers;

import androidx.annotation.NonNull;

import java.lang.reflect.Constructor;

import it.pietroterracciano.kudos.Modules.GoogleModule.SingleSignOnModule.Handlers.GoogleSSOHandler;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;

public class GoogleHandler
{
    @NonNull
    private static final Object
        _ol = new Object();
    @NonNull
    private static final Constructor<GoogleSSOHandler>
        __cgssoh = ConstructorUtils.getDeclared(GoogleSSOHandler.class);
    @NonNull
    private static GoogleSSOHandler
        __gssoh;

    private GoogleHandler() {}

    @NonNull
    public static GoogleSSOHandler handleSingleSignOn()
    {
        synchronized (_ol)
        {
            if(__gssoh == null) __gssoh = ConstructorUtils.newInstance(__cgssoh);
            return __gssoh;
        }
    }

}
