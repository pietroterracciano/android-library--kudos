package it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Chains;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONObject;

import java.util.List;

import it.pietroterracciano.kudos.Constants.CString;
import it.pietroterracciano.kudos.Utils.ThreadUtils;
import it.pietroterracciano.kudos.Modules.FacebookModule.Handlers.FacebookHandler;
import it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Enums.EFacebookGraphRequest;
import it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Interfaces.IFacebookGraphChain;
import it.pietroterracciano.kudos.Modules.FacebookModule.SingleSignOnModule.Interfaces.IFacebookGraphOnResult;
import it.pietroterracciano.kudos.Utils.ListUtils;

public class
    FacebookGraphChain
implements
    IFacebookGraphChain
{
    @Nullable
    private AccessToken _at;
    @Nullable
    private EFacebookGraphRequest _e;
    @Nullable
    private IFacebookGraphOnResult _or;
    @Nullable
    private List<String> _l;

    private FacebookGraphChain() {}

    @NonNull
    public IFacebookGraphChain setAccessToken(@Nullable AccessToken at)
    {
        _at = at;
        return this;
    }
    @NonNull
    public IFacebookGraphChain setRequest(@Nullable EFacebookGraphRequest e)
    {
        _e = e;
        return this;
    }
    @NonNull
    public IFacebookGraphChain setOnResult(@Nullable IFacebookGraphOnResult or)
    {
        _or = or;
        return this;
    }
    @NonNull
    public IFacebookGraphChain setRequestedParameters(@Nullable String... a)
    {
        _l = ListUtils.from(a);
        return this;
    }
    @NonNull
    public IFacebookGraphChain setRequestedParameters(@Nullable List<String> l)
    {
        _l = l;
        return this;
    }

    @NonNull
    public int executeAsync()
    {
        return
            ThreadUtils.runOnBackground
            (
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        execute();
                    }
                }
            );
    }

    public void execute()
    {
        if(_e == null)
            return;

        if(_at == null)
        {
            _at = FacebookHandler.handleSingleSignOn().getCurrentAccessToken();
            if(_at == null)
                return;
        }

        GraphRequest
            gr;

        switch (_e)
        {
            case Me:
                try
                {
                    gr =
                        GraphRequest.newMeRequest(
                            _at,
                            new GraphRequest.GraphJSONObjectCallback()
                            {
                                @Override
                                public void onCompleted(@Nullable JSONObject jsonObject, @Nullable GraphResponse gr)
                                {
                                    if(_or != null)
                                        _or.onCompleted(gr);
                                }
                            }
                        );
                }
                catch (Exception ignored)
                {
                    gr = null;
                }
                break;
            default:
                gr = null;
                break;
        }

        if(gr == null)
            return;

        if(_l != null)
        {
            Bundle bnd = new Bundle();
            bnd.putString("fields", String.join(CString.Comma, _l));
            try { gr.setParameters(bnd); } catch (Exception ignored){}
        }

        try { gr.executeAndWait(); } catch (Exception ignored) {}
    }
}