package it.pietroterracciano.kudos.Modules.ThumbnailModule.Listeners;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface ITHOnDrawableActionListener
{

    void onAction(@Nullable ImageView iv, @Nullable Drawable drw);
}
