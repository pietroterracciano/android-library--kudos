package it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Handlers;

import androidx.annotation.NonNull;

import java.lang.reflect.Constructor;

import it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Chains.FacebookGraphChain;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;

public class FacebookGraphHandler
{
    @NonNull
    private static final Constructor<FacebookGraphChain>
        __cfgc = ConstructorUtils.getDeclared(FacebookGraphChain.class);

    private FacebookGraphHandler() {}

    @NonNull
    public FacebookGraphChain prepareChain()
    {
        return ConstructorUtils.newInstance(__cfgc);
    }
}
