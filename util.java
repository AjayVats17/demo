package com.example.polestaruser.imageslider;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class util {

    public static void showError(AppCompatActivity ap, String msg){

        AlertDialog.Builder i=new AlertDialog.Builder(ap);
        i.setMessage(msg);
        i.setCancelable(false);
        i.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        i.show();
        i.setTitle(msg);
    }
}
