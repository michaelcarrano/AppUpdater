package com.github.javiersantos.appupdater;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.github.javiersantos.appupdater.enums.UpdateFrom;

import java.net.URL;

class UtilsDisplay {

    static void showUpdateAvailableDialog(final Context context, String title, String content, String btnNegative, String btnPositive, String btnNeutral, Boolean indefinite, final UpdateFrom updateFrom, final URL apk) {
        final LibraryPreferences libraryPreferences = new LibraryPreferences(context);

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(content);
        dialog.setPositiveButton(btnPositive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UtilsLibrary.goToUpdate(context, updateFrom, apk);
            }
        });

        if (!indefinite) {
            dialog.setNeutralButton(btnNeutral, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    libraryPreferences.setAppUpdaterShow(false);
                }
            });
            dialog.setNegativeButton(btnNegative, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }
        dialog.setCancelable(!indefinite);
        dialog.show();
    }

    static void showUpdateNotAvailableDialog(final Context context, String title, String content) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton(context.getResources().getString(android.R.string.ok), null)
                .show();
    }

    static void showUpdateAvailableSnackbar(final Context context, String content, Boolean indefinite, final UpdateFrom updateFrom, final URL apk) {
        Activity activity = (Activity) context;
        int snackbarTime = indefinite ? Snackbar.LENGTH_INDEFINITE : Snackbar.LENGTH_LONG;

        /*if (indefinite) {
            snackbarTime = Snackbar.LENGTH_INDEFINITE;
        } else {
            snackbarTime = Snackbar.LENGTH_LONG;
        }*/

        Snackbar snackbar = Snackbar.make(activity.findViewById(android.R.id.content), content, snackbarTime);
        snackbar.setAction(context.getResources().getString(R.string.appupdater_btn_update), new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UtilsLibrary.goToUpdate(context, updateFrom, apk);
            }
        }).show();
    }

    static void showUpdateNotAvailableSnackbar(final Context context, String content, Boolean indefinite) {
        Activity activity = (Activity) context;
        int snackbarTime = indefinite ? Snackbar.LENGTH_INDEFINITE : Snackbar.LENGTH_LONG;

        /*if (indefinite) {
            snackbarTime = Snackbar.LENGTH_INDEFINITE;
        } else {
            snackbarTime = Snackbar.LENGTH_LONG;
        }*/


        Snackbar.make(activity.findViewById(android.R.id.content), content, snackbarTime).show();
    }

}
