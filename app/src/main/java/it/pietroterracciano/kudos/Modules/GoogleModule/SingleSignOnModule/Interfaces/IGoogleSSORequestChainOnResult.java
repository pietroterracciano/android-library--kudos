package it.pietroterracciano.kudos.Modules.GoogleModule.SingleSignOnModule.Interfaces;

import androidx.annotation.NonNull;

import com.facebook.login.LoginResult;

public interface IGoogleSSORequestChainOnResult
{
    void onResumeWith(@NonNull Object o);
}
