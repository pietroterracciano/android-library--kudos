package it.pietroterracciano.kudos.Modules.FacebookModule.Handlers;

import androidx.annotation.NonNull;

import java.lang.reflect.Constructor;

import it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Handlers.FacebookGraphHandler;
import it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Handlers.FacebookSSOHandler;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;

public class FacebookHandler
{
    @NonNull
    private static final Object
        _ol = new Object();
    @NonNull
    private static final Constructor<FacebookSSOHandler>
        __cfssoh = ConstructorUtils.getDeclared(FacebookSSOHandler.class);
    @NonNull
    private static final Constructor<FacebookGraphHandler>
        __cfgh = ConstructorUtils.getDeclared(FacebookGraphHandler.class);
    @NonNull
    private static FacebookSSOHandler
        __fssoh;
    @NonNull
    private static FacebookGraphHandler
        __fgh;

    private FacebookHandler() {}

    @NonNull
    public static FacebookSSOHandler handleSingleSignOn()
    {
        synchronized (_ol)
        {
            if(__fssoh == null) __fssoh = ConstructorUtils.newInstance(__cfssoh);
            return __fssoh;
        }
    }

    @NonNull
    public static FacebookGraphHandler handleGraph()
    {
        synchronized (_ol)
        {
            if(__fgh == null) __fgh = ConstructorUtils.newInstance(__cfgh);
            return __fgh;
        }
    }
}