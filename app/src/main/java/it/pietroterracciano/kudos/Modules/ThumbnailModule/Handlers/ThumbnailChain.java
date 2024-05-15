package it.pietroterracciano.kudos.Modules.ThumbnailModule.Handlers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import it.pietroterracciano.kudos.Controllers.ThreadController;
import it.pietroterracciano.kudos.Kudos;
import it.pietroterracciano.kudos.Modules.ThumbnailModule.Enums.ETNDownsampleStrategy;
import it.pietroterracciano.kudos.Modules.ThumbnailModule.Listeners.ITHOnGenericActionListener;
import it.pietroterracciano.kudos.Modules.ThumbnailModule.Listeners.ITHOnDrawableActionListener;
import it.pietroterracciano.kudos.Utils.Views.ImageViewUtils;

public class ThumbnailChain
{
    @Nullable
    private Context
        _oContext;

    @Nullable
    private ImageView
        x_ivTarget;

    @Nullable
    private String
        _sURI;

    @Nullable
    private Bitmap.CompressFormat
        _eCompressFormat;

    @Nullable
    private Integer
        _iQuality,
        _iTimeout,
        _iWidth,
        _iHeight;

    @Nullable
    private DiskCacheStrategy
        _eDiskCacheStrategy;

    @Nullable
    private ETNDownsampleStrategy
        _eDownsampleStrategy;

    @Nullable
    private Boolean
        _bIsMemoryCacheEnabled,
        _bIsAutoloadDrawableEnabled;

    @Nullable
    private ITHOnGenericActionListener
        _lstOnStart,
        _lstOnEnd;

    @Nullable
    private ITHOnDrawableActionListener
        _lstOnFail,
        _lstOnSuccess;

    @Nullable
    private Drawable
        _drwOnFail;

    @Nullable
    private RequestOptions
        _oRequestOptions;

    @NonNull
    public ThumbnailChain setContext(@Nullable Context cnt) { _oContext = cnt; return this; }

    @NonNull
    public ThumbnailChain setURI(@Nullable String s) { _sURI = s; return this; }

    @NonNull
    public ThumbnailChain setTimeout(@Nullable @IntRange(from = 0) Integer i) { _iTimeout = i; return this; }

    @NonNull
    public ThumbnailChain setSize(@Nullable @IntRange(from = 0) Integer i, @Nullable @IntRange(from = 0) Integer j) { _iWidth = i; _iHeight = j; return this; }

    @NonNull
    public ThumbnailChain setCompressFormat(@Nullable Bitmap.CompressFormat e) { _eCompressFormat = e; return this; }

    @NonNull
    public ThumbnailChain setTarget(@Nullable ImageView iv) { x_ivTarget = iv; return this; }

    @NonNull
    public ThumbnailChain isAutoloadDrawableEnabled(@Nullable Boolean b) { _bIsAutoloadDrawableEnabled = b; return this; }

    @NonNull
    public ThumbnailChain setDownsampleStrategy(@Nullable ETNDownsampleStrategy e) {  _eDownsampleStrategy = e; return this; }

    @NonNull
    public ThumbnailChain setQuality(@Nullable @IntRange(from = 0, to = 100) Integer i) {  _iQuality = i; return this; }

    @NonNull
    public ThumbnailChain setDiskCacheStrategy(@Nullable DiskCacheStrategy e) { _eDiskCacheStrategy = e; return this; }

    @NonNull
    public ThumbnailChain isMemoryCacheEnabled(@Nullable Boolean b) {  _bIsMemoryCacheEnabled = b; return this; }

    @NonNull
    public ThumbnailChain setDrawableOnFail(@Nullable Drawable drw) { _drwOnFail = drw; return this;}

    @NonNull
    public ThumbnailChain onFail(@Nullable ITHOnDrawableActionListener lst) { _lstOnFail = lst; return this;}
    @NonNull
    public ThumbnailChain onSuccess(@Nullable ITHOnDrawableActionListener lst) { _lstOnSuccess = lst; return this;}

    @NonNull
    public ThumbnailChain onStart(@Nullable ITHOnGenericActionListener lst) { _lstOnStart = lst; return this;}
    @NonNull
    public ThumbnailChain onEnd(@Nullable ITHOnGenericActionListener lst) { _lstOnEnd = lst; return this;}

    private ThumbnailChain() {}

    public void process()
    {
        ThreadController.runOnBackground(new Runnable()
        {
            @Override
            public void run()
            {
                invokeGenericActionListener(x_ivTarget, _lstOnStart);

                if(_oContext == null)
                {
                    if(x_ivTarget != null)
                        _oContext = x_ivTarget.getContext();

                    if(_oContext == null)
                    {
                        _oContext = Kudos.getContext();

                        if(_oContext == null)
                            return;
                    }
                }

                _oRequestOptions = new RequestOptions();

                if(_eCompressFormat != null)
                    _oRequestOptions = _oRequestOptions.encodeFormat(_eCompressFormat);

                if(_eDiskCacheStrategy != null)
                    _oRequestOptions = _oRequestOptions.diskCacheStrategy(_eDiskCacheStrategy);

                if(_bIsMemoryCacheEnabled != null)
                    _oRequestOptions = _oRequestOptions.skipMemoryCache(!_bIsMemoryCacheEnabled);

                if(_iQuality != null)
                {
                    if(_iQuality < 0)
                        _iQuality = 0;
                    else if(_iQuality > 100)
                        _iQuality = 100;

                    _oRequestOptions = _oRequestOptions.encodeQuality(_iQuality);
                }

                if(_iTimeout != null)
                    _oRequestOptions = _oRequestOptions.timeout(_iTimeout);

                boolean
                    bHasWidth, bHasHeight;

                if(_iWidth != null)
                {
                    if(_iWidth < 0)
                        _iWidth = 0;

                    bHasWidth = true;
                }
                else
                    bHasWidth = false;

                if(_iHeight != null)
                {
                    if(_iHeight < 0)
                        _iHeight = 0;

                    bHasHeight = true;
                }
                else
                    bHasHeight = false;

                if(bHasWidth && bHasHeight)
                    _oRequestOptions = _oRequestOptions.override(_iWidth, _iHeight);
                else if(bHasWidth)
                    _oRequestOptions = _oRequestOptions.override(_iWidth);
                else if(bHasHeight)
                    _oRequestOptions = _oRequestOptions.override(_iHeight);

                if(_eDownsampleStrategy != null)
                    switch (_eDownsampleStrategy)
                    {
                        case CenterCrop:
                            _oRequestOptions.optionalCenterCrop();
                            break;
                        case CenterInside:
                            _oRequestOptions.optionalCenterInside();
                            break;
                        case CircleCrop:
                            _oRequestOptions.optionalCircleCrop();
                            break;
                        case FitCenter:
                            _oRequestOptions.optionalFitCenter();
                            break;
                        case None:
                        default:
                            _oRequestOptions.dontTransform();
                            break;
                    }

                if(_bIsAutoloadDrawableEnabled == null)
                    _bIsAutoloadDrawableEnabled = false;

                ThreadController.runOnForeground(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            Glide
                                .with(_oContext)
                                .load(_sURI)
                                .apply(_oRequestOptions)
                                .listener
                                (
                                    new RequestListener<Drawable>()
                                    {
                                        @Override
                                        public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource)
                                        {
                                            if(_bIsAutoloadDrawableEnabled) invokeAutoload(x_ivTarget, _drwOnFail);
                                            invokeDrawableActionListener(x_ivTarget, _drwOnFail, _lstOnFail);
                                            invokeGenericActionListener(x_ivTarget, _lstOnEnd);
                                            return false;
                                        }

                                        @Override
                                        public boolean onResourceReady(@NonNull Drawable drw, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource)
                                        {
                                            if(_bIsAutoloadDrawableEnabled) invokeAutoload(x_ivTarget, drw);
                                            invokeDrawableActionListener(x_ivTarget, drw, _lstOnSuccess);
                                            invokeGenericActionListener(x_ivTarget, _lstOnEnd);
                                            return false;
                                        }
                                    }
                                )
                                .dontAnimate()
                                .submit();
                        }
                        catch (Exception e)
                        {
                            if(_bIsAutoloadDrawableEnabled) invokeAutoload(x_ivTarget, _drwOnFail);
                            invokeDrawableActionListener(x_ivTarget, _drwOnFail, _lstOnFail);
                            invokeGenericActionListener(x_ivTarget, _lstOnEnd);
                        }
                    }
                });
            }
        });
    }

    private static void invokeDrawableActionListener(@Nullable ImageView iv, @Nullable Drawable drw, @Nullable ITHOnDrawableActionListener lst)
    {
        if(lst == null) return;

        ThreadController.runOnForeground(new Runnable() {
            @Override
            public void run()
            {
                try { lst.onAction(iv, drw); } catch (Exception ignored) {}
            }
        });
    }

    private static void invokeGenericActionListener(@Nullable ImageView iv, @Nullable ITHOnGenericActionListener lst)
    {
        if(lst == null) return;
        try { lst.onAction(iv); } catch (Exception ignored) {}
    }

    private static void invokeAutoload(@Nullable ImageView iv, @Nullable Drawable drw)
    {
        ThreadController.runOnForeground(new Runnable() {
            @Override
            public void run()
            {
                ImageViewUtils.setDrawable(iv, drw);
            }
        });
    }
}