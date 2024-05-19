package it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Chains;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.List;

import it.pietroterracciano.kudos.Controllers.ThreadController;
import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Enums.EFacebookSSOScope;
import it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Interfaces.IFacebookSSOChain;
import it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Interfaces.IFacebookSSOOnResult;
import it.pietroterracciano.kudos.Utils.ListUtils;

public class
FacebookSSOChain
implements
        IFacebookSSOChain
{
    @NonNull
    private static final Exception
        _eBadRequest = new Exception("Bad request");

    @Nullable
    private AppCompatActivity _aca;
    @Nullable
    private Fragment _frg;
    @Nullable
    private EFacebookSSOScope _eScope;
    @Nullable
    private IFacebookSSOOnResult _or;
    @Nullable
    private List<String> _lRequestedPermissions;
    @NonNull
    private final CallbackManager _cm;
    @NonNull
    private final LoginManager _lm;
    @NonNull
    private final FacebookCallback<LoginResult> _fc;

    private FacebookSSOChain()
    {
        _cm = CallbackManager.Factory.create();
        _lm = LoginManager.getInstance();
        _fc = new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(@NonNull LoginResult lr)
            {
                onResult_onSuccess(lr);
            }

            @Override
            public void onCancel()
            {
                onResult_onCancel();
            }

            @Override
            public void onError(@NonNull FacebookException e)
            {
                onResult_onError(e);
            }
        };
    }

    @NonNull
    @Override
    public IFacebookSSOChain setActivity(@Nullable AppCompatActivity aca)
    {
        _aca = aca;
        return this;
    }
    @NonNull
    @Override
    public IFacebookSSOChain setFragment(@Nullable Fragment frg)
    {
        _frg = frg;
        return this;
    }
    @NonNull
    @Override
    public IFacebookSSOChain setScope(@Nullable EFacebookSSOScope e)
    {
        _eScope = e;
        return this;
    }
    @NonNull
    @Override
    public IFacebookSSOChain setRequestedPermissions(@Nullable String... a)
    {
        _lRequestedPermissions = ListUtils.from(a);
        return this;
    }
    @NonNull
    @Override
    public IFacebookSSOChain setRequestedPermissions(@Nullable List<String> l)
    {
        _lRequestedPermissions = l;
        return this;
    }
    @NonNull
    @Override
    public IFacebookSSOChain setOnResult(@Nullable IFacebookSSOOnResult or)
    {
        try { _lm.unregisterCallback(_cm); } catch (Exception e){ onResult_onError(e);}
        _or = or;
        try { _lm.registerCallback(_cm, _fc); } catch (Exception e){ onResult_onError(e);}
        return this;
    }

    @NonNull
    @Override
    public int executeAsync()
    {
        return
            ThreadController.runOnBackground(new Runnable()
            {
                @Override
                public void run()
                {
                    execute();
                }
            });
    }

    @Override
    public void execute()
    {
        if
        (
            _eScope == null
            || _lRequestedPermissions == null
        )
        {
            onResult_onError(_eBadRequest);
            return;
        }

        if(_frg == null && _aca == null)
        {
            _aca = Kudos.getActivity();

            if(_aca == null)
            {
                onResult_onError(_eBadRequest);
                return;
            }
        }

        switch (_eScope)
        {
            case Read:
                if(_aca != null)
                    try { _lm.logInWithReadPermissions(_aca, _cm, _lRequestedPermissions); } catch (Exception e){ onResult_onError(e);}
                else
                    try {_lm.logInWithReadPermissions(_frg, _cm, _lRequestedPermissions); } catch (Exception e){ onResult_onError(e);}
                break;
            case Publish:
                if(_aca != null)
                    try {_lm.logInWithPublishPermissions(_aca, _cm, _lRequestedPermissions);} catch (Exception e){ onResult_onError(e);}
                else
                    try {_lm.logInWithPublishPermissions(_frg, _cm, _lRequestedPermissions);} catch (Exception e){ onResult_onError(e);}
                break;
            default: case Standard:
                if(_aca != null)
                    try {_lm.logIn(_aca, _cm, _lRequestedPermissions);} catch (Exception e){ onResult_onError(e);}
                else
                    try {_lm.logIn(_frg, _lRequestedPermissions);} catch (Exception e){ onResult_onError(e);}
                break;
        }
    }

    private void onResult_onSuccess(@NonNull LoginResult lr)
    {
        if(_or == null) return;
        try { _or.onSuccess(lr); } catch (Exception ignored) {}
    }

    private void onResult_onCancel()
    {
        if(_or == null) return;
        try { _or.onCancel(); } catch (Exception ignored) {}
    }

    private void onResult_onError(@NonNull Exception e)
    {
        if(_or == null) return;
        try { _or.onError(e); } catch (Exception ignored) {}
    }
}