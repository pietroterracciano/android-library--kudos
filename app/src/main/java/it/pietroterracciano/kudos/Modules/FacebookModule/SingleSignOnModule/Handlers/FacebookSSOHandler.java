package it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Handlers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.AccessToken;

import java.lang.reflect.Constructor;

import it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Chains.FacebookSSOChain;
import it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Interfaces.IFacebookSSOChain;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;

public class FacebookSSOHandler
{
    @NonNull
    private static final Constructor<FacebookSSOChain>
        __cfssoc = ConstructorUtils.getDeclared(FacebookSSOChain.class);

    private FacebookSSOHandler() {}

    @NonNull
    public IFacebookSSOChain prepareChain()
    {
        return ConstructorUtils.newInstance(__cfssoc);
    }

    @Nullable
    public AccessToken getCurrentAccessToken()
    {
        try
        {
            return AccessToken.getCurrentAccessToken();
        }
        catch (Exception ignored)
        {
            return null;
        }
    }
}
