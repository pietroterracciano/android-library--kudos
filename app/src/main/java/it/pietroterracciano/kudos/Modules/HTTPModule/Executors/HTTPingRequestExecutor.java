package it.pietroterracciano.kudos.Modules.HTTPModule.Executors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.pietroterracciano.kudos.Constants.CString;
import it.pietroterracciano.kudos.Utils.ThreadUtils;
import it.pietroterracciano.kudos.Modules.HTTPModule.Constants.CHTTPHeader;
import it.pietroterracciano.kudos.Modules.HTTPModule.Enums.EHTTPCharset;
import it.pietroterracciano.kudos.Modules.HTTPModule.Enums.EHTTPConnection;
import it.pietroterracciano.kudos.Modules.HTTPModule.Enums.EHTTPContentType;
import it.pietroterracciano.kudos.Modules.HTTPModule.Enums.EHTTPMethod;
import it.pietroterracciano.kudos.Modules.HTTPModule.Listeners.IHTTPingOnResponseReceiveListener;
import it.pietroterracciano.kudos.Modules.HTTPModule.Payloads.AHTTPingPayload;
import it.pietroterracciano.kudos.Modules.HTTPModule.Types.HTTPingResponse;
import it.pietroterracciano.kudos.Modules.HTTPModule.Utils.HTTPConnectionUtils;
import it.pietroterracciano.kudos.Modules.HTTPModule.Utils.HTTPContentTypeUtils;
import it.pietroterracciano.kudos.Modules.HTTPModule.Utils.HTTPMethodUtils;
import it.pietroterracciano.kudos.Utils.Connections.HttpURLConnectionUtils;
import it.pietroterracciano.kudos.Utils.ConstructorUtils;
import it.pietroterracciano.kudos.Utils.MethodUtils;
import it.pietroterracciano.kudos.Utils.StreamUtils;
import it.pietroterracciano.kudos.Utils.Connections.URLConnectionUtils;
import it.pietroterracciano.kudos.Utils.URLUtils;

public final class HTTPingRequestExecutor
{
    @NonNull
    private static final Constructor<HTTPingResponse>
        _chttpingresponse = ConstructorUtils.getDeclared(HTTPingResponse.class, HttpURLConnection.class);

    @NonNull
    private static final Method
        _mhttpingp_write = MethodUtils.getDeclared(AHTTPingPayload.class, "_write", OutputStream.class);

    @NonNull
    private final Object _lck;
    @Nullable
    private EHTTPCharset _ehttpchr;
    @Nullable
    private EHTTPMethod _ehttpm;
    @Nullable
    private EHTTPConnection _ehttpcnn;
    @NonNull
    private final HashMap<String, String> _hmHeaders;
    @Nullable
    private URL _url;
    @Nullable
    private HttpURLConnection _httpurlc;
    @Nullable
    private Integer _ict, _irt, _iwbs;
    @NonNull
    private final List<AHTTPingPayload<?>> _lhttpingp;
    @Nullable
    private EHTTPContentType _ehttpct;

    @NonNull
    public HTTPingRequestExecutor setURL(@Nullable URI uri) { _url = URLUtils.convert(uri); return this; }
    @NonNull
    public HTTPingRequestExecutor setURL(@Nullable String url) { _url = URLUtils.convert(url); return this; }
    @NonNull
    public HTTPingRequestExecutor setURL(@Nullable URL url) { _url = url; return this; }
    @NonNull
    public HTTPingRequestExecutor setMethod(@Nullable EHTTPMethod e) { _ehttpm = e; return this; }
    @NonNull
    public HTTPingRequestExecutor setCharset(@Nullable EHTTPCharset e) { _ehttpchr = e; return this; }
    @NonNull
    public HTTPingRequestExecutor setContentType(@Nullable EHTTPContentType ehttpct) {_ehttpct = ehttpct; return  this;}
    @NonNull
    public HTTPingRequestExecutor setConnection(@Nullable EHTTPConnection e) { _ehttpcnn = e; return this; }
    @NonNull
    public HTTPingRequestExecutor setWriteBufferSize(@Nullable Integer i) { _iwbs = i; return this; }
    @NonNull
    public HTTPingRequestExecutor setConnectionTimeoutInMilliseconds(@Nullable Integer i) { _ict = i; return this; }
    @NonNull
    public HTTPingRequestExecutor setReadTimeoutInMilliseconds(@Nullable Integer i) { _irt = i; return this; }
    @NonNull
    public HTTPingRequestExecutor putHeader(@Nullable String sk, @Nullable String sv) { if(sk != null) _hmHeaders.put(sk, sv); return this; }

    private HTTPingRequestExecutor()
    {
        _lck = new Object();
        _lhttpingp = new ArrayList<>();
        _hmHeaders = new HashMap<>();
    }

    public final HTTPingResponse execute()
    {
        if(_url == null)
            return null;

        try { System.setProperty("http.keepAlive", "false"); } catch (Exception ignored){}
        try { System.setProperty("java.net.preferIPv4Stack", "true"); } catch (Exception ignored){}

        URLConnection
            urlc = URLConnectionUtils.openConnection(_url);

        if(urlc == null)
            return null;

        URLConnectionUtils.setUseCaches(urlc, false);
        URLConnectionUtils.setDefaultUseCaches(urlc, false);
        if(_ict != null) URLConnectionUtils.setConnectTimeout(urlc, _ict );
        if(_irt != null) URLConnectionUtils.setReadTimeout(urlc, _irt );
        URLConnectionUtils.setDoInput(urlc, true);

        for(Map.Entry<String, String> meHeader : _hmHeaders.entrySet())
        {
            if(meHeader == null) continue;
            URLConnectionUtils.setRequestProperty(urlc, meHeader.getKey(), meHeader.getValue());
        }

        URLConnectionUtils.setRequestProperty(urlc, CHTTPHeader.Connection, HTTPConnectionUtils.convert(_ehttpcnn));
        URLConnectionUtils.setRequestProperty(urlc, CHTTPHeader.ContentType, HTTPContentTypeUtils.convert(_ehttpct));

        _httpurlc = HttpURLConnectionUtils.convert(urlc);
        if(_httpurlc == null) return null;


        HttpURLConnectionUtils.setInstanceFollowRedirects(_httpurlc, false);
        HttpURLConnectionUtils.setRequestMethod(_httpurlc, HTTPMethodUtils.convert(_ehttpm));

        if(!_writeOnOutputStream())
            URLConnectionUtils.setRequestProperty(urlc, CHTTPHeader.ContentLength, CString.Zero);

        return ConstructorUtils.createInstance(_chttpingresponse, _httpurlc);
    }

    public final void executeAsync(@Nullable IHTTPingOnResponseReceiveListener lst)
    {
        ThreadUtils.runOnBackground(new Runnable()
        {
            @Override
            public void run()
            {
                HTTPingResponse httpingr = execute();
                if(lst != null) lst.onResponseReceive(httpingr);
            }
        });
    }

    public final HTTPingRequestExecutor addPayload(@Nullable AHTTPingPayload<?> httpingp)
    {
        if(httpingp != null && httpingp.ContentType == _ehttpct)
            _lhttpingp.add(httpingp);

        return this;
    }

    private boolean _writeOnOutputStream()
    {
        if
        (
            _httpurlc == null
            || _lhttpingp.isEmpty()
        )
            return false;

        try { _httpurlc.setDoOutput(true); }
        catch (Exception ignored) { return false; }

        OutputStream os;
        try { os = _httpurlc.getOutputStream(); }
        catch (Exception oException) { os = null; }
        if(os == null)
            return false;

        if(!(os instanceof BufferedOutputStream))
        {
            if(_iwbs != null && _iwbs > 0)
                os = StreamUtils.createBufferedOutputStream(os, _iwbs);
            else
                os = StreamUtils.createBufferedOutputStream(os);

            if(os == null)
                return false;
        }

        int j = _lhttpingp.size();

        for(int i=0; i<j; i++)
            MethodUtils.invoke(_lhttpingp.get(i), _mhttpingp_write, os);

        /*
        while(itr.hasNext())
        {
            MethodUtils.invoke(itr.next(), _mhttpingp_write, os);
            //Boolean b = MethodUtils.invoke(itr.next(), _mhttpingp_write, os);
            //if( b == null || !b) continue;
        }*/

        return StreamUtils.close(os);
    }

    public final boolean abort()
    {
        if(_httpurlc != null)
            try
            {
                _httpurlc.disconnect();
                return true;
            }
            catch (Exception ignored)
            {
            }

        return false;
    }
}
