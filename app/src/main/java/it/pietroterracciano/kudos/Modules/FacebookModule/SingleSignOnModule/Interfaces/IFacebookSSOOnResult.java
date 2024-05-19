package it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Interfaces;

import androidx.annotation.NonNull;

import com.facebook.login.LoginResult;

public interface IFacebookSSOOnResult
{
    void onSuccess(@NonNull LoginResult lr);
    void onCancel();
    void onError(@NonNull Exception e);
}
