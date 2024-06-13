package it.pietroterracciano.kudos.Modules.ThumbnailModule.Listeners;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public interface IThumbnailingOnDrawableActionListener
{

    void onAction(@Nullable ImageView iv, @Nullable Drawable drw);
}
