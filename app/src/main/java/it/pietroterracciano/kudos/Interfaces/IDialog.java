package it.pietroterracciano.kudos.Interfaces;

import android.content.DialogInterface;

import androidx.annotation.NonNull;

import it.pietroterracciano.kudos.Behaviors.ADialogBehavior;

public interface IDialog extends DialogInterface
{
    @NonNull
    ADialogBehavior getBaseBehavior();
}
