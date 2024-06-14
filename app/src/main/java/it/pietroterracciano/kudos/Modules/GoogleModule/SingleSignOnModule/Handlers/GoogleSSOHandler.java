package it.pietroterracciano.kudos.Modules.GoogleModule.SingleSignOnModule.Handlers;

import androidx.annotation.NonNull;

import java.lang.reflect.Constructor;

import it.pietroterracciano.kudos.Modules.GoogleModule.SingleSignOnModule.Chains.GoogleSSORequestChain;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;

public class GoogleSSOHandler
{
    @NonNull
    private static final Constructor<GoogleSSORequestChain>
        __cgssoc = ConstructorUtils.getDeclared(GoogleSSORequestChain.class);

    private GoogleSSOHandler() {}

    @NonNull
    public GoogleSSORequestChain prepareRequestChain()
    {
        return ConstructorUtils.createInstance(__cgssoc);
    }
}