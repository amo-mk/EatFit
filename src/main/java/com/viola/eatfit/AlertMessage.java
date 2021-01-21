package com.viola.eatfit;

import android.app.AlertDialog;
import android.content.Context;

public class AlertMessage {

    public AlertMessage() {
    }

    public void showMessage(Context ct, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ct);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
