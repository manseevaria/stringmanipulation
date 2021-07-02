package com.practicaltask;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

public class Utils {
    public static View alertView;
    public static AlertDialog myDialog;

    public static boolean validateString(String object) {
        boolean flag = false;
        if (object != null && !object.isEmpty()
                && object.equalsIgnoreCase("null") != true
                && object.trim().length() > 0
                && !object.equalsIgnoreCase("(null)")) {
            flag = true;
        }
        return flag;
    }
    public static void showMessageOK(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        alertView = inflater.inflate(R.layout.alert, null);
        builder.setView(alertView);
        builder.setCancelable(true);
        TextView btn_save_username = (TextView) alertView.findViewById(R.id.btn_ok_dialog);
        TextView txt_status = (TextView) alertView.findViewById(R.id.result);
        TextView txt_msg = (TextView) alertView.findViewById(R.id.msg);

        txt_status.setText(title);
        txt_msg.setText(message);
        btn_save_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        myDialog = builder.create();
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
}
