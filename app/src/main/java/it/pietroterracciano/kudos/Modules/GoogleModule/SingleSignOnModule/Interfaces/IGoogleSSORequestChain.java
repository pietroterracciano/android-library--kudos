package it.pietroterracciano.kudos.Modules.GoogleModule.SingleSignOnModule.Interfaces;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface IGoogleSSORequestChain
{
    @NonNull
    IGoogleSSORequestChain setActivity(@Nullable Activity act);
    @NonNull
    IGoogleSSORequestChain isFilterByAuthorizedAccountsEnabled(@NonNull boolean b);
    @NonNull
    IGoogleSSORequestChain setServerClientID(@Nullable String s);
    @NonNull
    IGoogleSSORequestChain isAutoSelectEnabled(@NonNull boolean b);
    @NonNull
    IGoogleSSORequestChain setNonce(@Nullable String s);
    @NonNull
    IGoogleSSORequestChain isAutoNonceEnabled(@NonNull boolean b);
    @NonNull
    IGoogleSSORequestChain setOnResult(@Nullable IGoogleSSORequestChainOnResult or);

    void execute();

    @NonNull
    int executeAsync();
}
