package it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Interfaces;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.AccessToken;

import java.util.List;

import it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Enums.EFacebookGraphRequest;

public interface IFacebookGraphChain
{
    @NonNull
    IFacebookGraphChain setAccessToken(@Nullable AccessToken at);
    @NonNull
    IFacebookGraphChain setRequest(@Nullable EFacebookGraphRequest e);
    @NonNull
    IFacebookGraphChain setOnResult(@Nullable IFacebookGraphOnResult or);
    @NonNull
    IFacebookGraphChain setRequestedParameters(@Nullable String... a);
    @NonNull
    IFacebookGraphChain setRequestedParameters(@Nullable List<String> l);

    void execute();
    @NonNull
    int executeAsync();
}
