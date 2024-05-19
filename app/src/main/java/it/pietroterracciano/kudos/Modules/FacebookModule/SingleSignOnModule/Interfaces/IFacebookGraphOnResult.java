package it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Interfaces;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

public interface IFacebookGraphOnResult
{
    void onCompleted(@Nullable GraphResponse gr);
}
