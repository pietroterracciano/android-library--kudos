package it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Interfaces;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.List;

import it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Enums.EFacebookSSOScope;

public interface IFacebookSSOChain
{
    @NonNull
    IFacebookSSOChain setActivity(@Nullable AppCompatActivity act);
    @NonNull
    IFacebookSSOChain setFragment(@Nullable Fragment frg);
    @NonNull
    IFacebookSSOChain setOnResult(@Nullable IFacebookSSOOnResult or);
    @NonNull
    IFacebookSSOChain setRequestedPermissions(@Nullable String... a);
    @NonNull
    IFacebookSSOChain setRequestedPermissions(@Nullable List<String> l);
    @NonNull
    IFacebookSSOChain setScope(@Nullable EFacebookSSOScope e);

    void execute();
    @NonNull
    int executeAsync();
}
