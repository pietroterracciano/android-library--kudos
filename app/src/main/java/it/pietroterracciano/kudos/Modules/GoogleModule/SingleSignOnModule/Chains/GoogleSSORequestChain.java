package it.pietroterracciano.kudos.Modules.GoogleModule.SingleSignOnModule.Chains;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.credentials.CredentialManager;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;

import com.google.android.libraries.identity.googleid.GetGoogleIdOption;

import it.pietroterracciano.kudos.Controllers.ThreadController;
import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Modules.GoogleModule.SingleSignOnModule.Interfaces.IGoogleSSORequestChain;
import it.pietroterracciano.kudos.Modules.GoogleModule.SingleSignOnModule.Interfaces.IGoogleSSORequestChainOnResult;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

public class
    GoogleSSORequestChain
implements
    IGoogleSSORequestChain
{
    @Nullable
    private Activity
        _act;
    @NonNull
    private static GetGoogleIdOption.Builder
        _ggidob;
    @NonNull
    private static Continuation<GetCredentialResponse>
        _cgcr;
    @Nullable
    private boolean
        _bIsAutoNonceEnabled;
    @Nullable
    private IGoogleSSORequestChainOnResult
        _or;

    private GoogleSSORequestChain()
    {
        _ggidob = new GetGoogleIdOption.Builder();
        _cgcr = new Continuation<GetCredentialResponse>()
        {
            @NonNull
            @Override
            public CoroutineContext getContext()
            {
                return EmptyCoroutineContext.INSTANCE;
            }

            @Override
            public void resumeWith(@NonNull Object o)
            {
                onResult_onResumeWith(o);
            }
        };
    }

    @NonNull
    @Override
    public IGoogleSSORequestChain setActivity(@Nullable Activity act)
    {
        _act = act;
        return this;
    }

    @NonNull
    @Override
    public IGoogleSSORequestChain setOnResult(@Nullable IGoogleSSORequestChainOnResult or)
    {
        _or = or;
        return this;
    }

    @NonNull
    @Override
    public IGoogleSSORequestChain isFilterByAuthorizedAccountsEnabled(@NonNull boolean b)
    {
        try { _ggidob.setFilterByAuthorizedAccounts(b);} catch (Exception ignored){}
        return this;
    }

    @NonNull
    @Override
    public IGoogleSSORequestChain isAutoSelectEnabled(@NonNull boolean b)
    {
        try { _ggidob.setAutoSelectEnabled(b);} catch (Exception ignored){}
        return this;
    }

    @NonNull
    @Override
    public IGoogleSSORequestChain setServerClientID(@Nullable String s)
    {
        if(s != null)
            try { _ggidob.setServerClientId(s);} catch (Exception ignored){}
        return this;
    }

    @NonNull
    @Override
    public IGoogleSSORequestChain setNonce(@Nullable String s)
    {
        try { _ggidob.setNonce(s);} catch (Exception ignored){}
        return this;
    }

    @NonNull
    @Override
    public IGoogleSSORequestChain isAutoNonceEnabled(@NonNull boolean b)
    {
        _bIsAutoNonceEnabled = b;
        return this;
    }

    @Override
    public void execute()
    {
        GetCredentialRequest.Builder
            gcrb;

        try { gcrb = new GetCredentialRequest.Builder(); } catch (Exception ignored )  {gcrb = null;}

        if(gcrb == null)
            return;

        try { gcrb.addCredentialOption(_ggidob.build()); } catch (Exception ignored) {}

        GetCredentialRequest
            gcr;

        try { gcr = gcrb.build(); } catch (Exception ignored) { gcr = null;}

        if(gcr == null)
            return;

        if(_act == null)
        {
            _act = Kudos.getActivity();
            if(_act == null)
                return;
        }

        CredentialManager
            cm;

        try { cm = CredentialManager.create(_act); } catch (Exception ignored) {cm = null;}

        if(cm == null)
            return;

        try
        {
            cm
                .getCredential(
                    _act,
                    gcr,
                    _cgcr
            );
        }
        catch (Exception ignored)
        {
        }
    }

    @Override
    @NonNull
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

    private void onResult_onResumeWith(@NonNull Object o)
    {
        if(_or == null) return;
        try { _or.onResumeWith(o); } catch (Exception ignored) {}
    }

    /*
    private void API33_execute()
    {
        GoogleSignInOptions gsio;
        try { gsio = _gsiob.build(); } catch (Exception ignored) {gsio = null;}

        if(gsio == null)
            return;

        GoogleSignInClient
                gsic;
        try { gsic = GoogleSignIn.getClient(_act, gsio); } catch (Exception ignored) {gsic = null;}

        if(gsic == null)
            return;

        prsaasova.launch(mGoogleSignInClient.getSignInIntent());

        registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>()
                {
                    @Override
                    public void onActivityResult(ActivityResult o)
                    {
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(o.getData());
                        handleSignInResult(task);

                    }
                }
        );
    }*/
}
