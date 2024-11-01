package org.android.themepicker.cl;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CustomLinearLayout extends LinearLayout {
    private LinearLayout other, daydream;

    public CustomLinearLayout(Context ctxt) {
        super(ctxt);
        init(ctxt);
    }

    public CustomLinearLayout(Context ctxt, AttributeSet attrs) {
        super(ctxt, attrs);
        init(ctxt);
    }

    private void init(Context ctxt) {
        LayoutInflater.from(ctxt).inflate(R.layout.custom_linear, this, true);
        other = findViewById(R.id.other);
        daydream = findViewById(R.id.daydream);
        other.setOnClickListener(
                v -> {
                    Intent intent = new Intent(Intent.ACTION_SET_WALLPAPER);
                    if (intent.resolveActivity(ctxt.getPackageManager()) != null) {
                        ctxt.startActivity(intent);
                    } else {
                        Toast.makeText(ctxt, "No App found", Toast.LENGTH_LONG).show();
                    }
                });
        daydream.setOnClickListener(
                v -> {
                    try {
                        ctxt.startActivity(new Intent(Settings.ACTION_DREAM_SETTINGS));
                    } catch (Exception err) {
                        Toast.makeText(ctxt, "An error occured", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
