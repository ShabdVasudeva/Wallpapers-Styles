package org.android.themepicker.cl;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.palette.graphics.Palette;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import org.android.themepicker.cl.databinding.ActivityColorsBinding;

public class MoreColorsActivity extends AppCompatActivity implements ColorPickerDialogListener {
    private ActivityColorsBinding binding;
    private StylesModel util;
    private static final String TAG = "MainActivity";
    private static final int DIALOG_ID = 0;
    private int color1,
            color2,
            color3,
            color4,
            color5,
            color6,
            color7,
            color11,
            color12,
            color13,
            color14,
            color15,
            color16,
            color17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityColorsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(
                v -> {
                    onBackPressed();
                });
        util = new ViewModelProvider(this).get(StylesModel.class);
        Bitmap homeBitmap = ((BitmapDrawable) util.retrieveHomeScreenWallpaper(this)).getBitmap();
        Bitmap lockBitmap = ((BitmapDrawable) util.retrieveLockScreenWallpaper(this)).getBitmap();
        Palette.from(homeBitmap)
                .generate(
                        new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                int vibrantColor = palette.getVibrantColor(Color.BLACK);
                                int lightVibrantColor = palette.getLightVibrantColor(Color.BLACK);
                                int darkVibrantColor = palette.getDarkVibrantColor(Color.BLACK);
                                int mutedColor = palette.getMutedColor(Color.BLACK);
                                int lightMutedColor = palette.getLightMutedColor(Color.BLACK);
                                int darkMutedColor = palette.getDarkMutedColor(Color.BLACK);
                                int dominantColor = palette.getDominantColor(Color.BLACK);
                                color1 = vibrantColor;
                                color2 = lightVibrantColor;
                                color3 = darkVibrantColor;
                                color4 = mutedColor;
                                color5 = lightMutedColor;
                                color6 = darkMutedColor;
                                color7 = dominantColor;
                                binding.fab1.setBackgroundTintList(
                                        ColorStateList.valueOf(vibrantColor));
                                binding.fab2.setBackgroundTintList(
                                        ColorStateList.valueOf(lightVibrantColor));
                                binding.fab3.setBackgroundTintList(
                                        ColorStateList.valueOf(darkVibrantColor));
                                binding.fab4.setBackgroundTintList(
                                        ColorStateList.valueOf(mutedColor));
                                binding.fab5.setBackgroundTintList(
                                        ColorStateList.valueOf(lightMutedColor));
                                binding.fab6.setBackgroundTintList(
                                        ColorStateList.valueOf(darkMutedColor));
                                binding.fab7.setBackgroundTintList(
                                        ColorStateList.valueOf(dominantColor));
                            }
                        });
        Palette.from(lockBitmap)
                .generate(
                        new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                int vibrantColor = palette.getVibrantColor(Color.BLACK);
                                int lightVibrantColor = palette.getLightVibrantColor(Color.BLACK);
                                int darkVibrantColor = palette.getDarkVibrantColor(Color.BLACK);
                                int mutedColor = palette.getMutedColor(Color.BLACK);
                                int lightMutedColor = palette.getLightMutedColor(Color.BLACK);
                                int darkMutedColor = palette.getDarkMutedColor(Color.BLACK);
                                int dominantColor = palette.getDominantColor(Color.BLACK);
                                color11 = vibrantColor;
                                color12 = lightVibrantColor;
                                color13 = darkVibrantColor;
                                color14 = mutedColor;
                                color15 = lightMutedColor;
                                color16 = darkMutedColor;
                                color17 = dominantColor;
                                binding.fab11.setBackgroundTintList(
                                        ColorStateList.valueOf(vibrantColor));
                                binding.fab12.setBackgroundTintList(
                                        ColorStateList.valueOf(lightVibrantColor));
                                binding.fab13.setBackgroundTintList(
                                        ColorStateList.valueOf(darkVibrantColor));
                                binding.fab14.setBackgroundTintList(
                                        ColorStateList.valueOf(mutedColor));
                                binding.fab15.setBackgroundTintList(
                                        ColorStateList.valueOf(lightMutedColor));
                                binding.fab16.setBackgroundTintList(
                                        ColorStateList.valueOf(darkMutedColor));
                                binding.fab17.setBackgroundTintList(
                                        ColorStateList.valueOf(dominantColor));
                            }
                        });
        binding.fab1.setOnClickListener(
                v -> {
                    ThemeCustomiser.applyCustomColors(
                            this, String.format("#%08X", color1), String.format("#%08X", color1));
                });
        binding.fab2.setOnClickListener(
                v -> {
                    ThemeCustomiser.applyCustomColors(
                            this, String.format("#%08X", color2), String.format("#%08X", color2));
                });
        binding.fab3.setOnClickListener(
                v -> {
                    ThemeCustomiser.applyCustomColors(
                            this, String.format("#%08X", color3), String.format("#%08X", color3));
                });
        binding.fab4.setOnClickListener(
                v -> {
                    ThemeCustomiser.applyCustomColors(
                            this, String.format("#%08X", color4), String.format("#%08X", color4));
                });
        binding.fab5.setOnClickListener(
                v -> {
                    ThemeCustomiser.applyCustomColors(
                            this, String.format("#%08X", color5), String.format("#%08X", color5));
                });
        binding.fab6.setOnClickListener(
                v -> {
                    ThemeCustomiser.applyCustomColors(
                            this, String.format("#%08X", color6), String.format("#%08X", color6));
                });
        binding.fab7.setOnClickListener(
                v -> {
                    ThemeCustomiser.applyCustomColors(
                            this, String.format("#%08X", color7), String.format("#%08X", color7));
                });
        binding.fab11.setOnClickListener(
                v -> {
                    ThemeCustomiser.applyCustomColors(
                            this, String.format("#%08X", color11), String.format("#%08X", color11));
                });
        binding.fab12.setOnClickListener(
                v -> {
                    ThemeCustomiser.applyCustomColors(
                            this, String.format("#%08X", color12), String.format("#%08X", color12));
                });
        binding.fab13.setOnClickListener(
                v -> {
                    ThemeCustomiser.applyCustomColors(
                            this, String.format("#%08X", color13), String.format("#%08X", color13));
                });
        binding.fab14.setOnClickListener(
                v -> {
                    ThemeCustomiser.applyCustomColors(
                            this, String.format("#%08X", color14), String.format("#%08X", color14));
                });
        binding.fab15.setOnClickListener(
                v -> {
                    ThemeCustomiser.applyCustomColors(
                            this, String.format("#%08X", color15), String.format("#%08X", color15));
                });
        binding.fab16.setOnClickListener(
                v -> {
                    ThemeCustomiser.applyCustomColors(
                            this, String.format("#%08X", color16), String.format("#%08X", color16));
                });
        binding.fab17.setOnClickListener(
                v -> {
                    ThemeCustomiser.applyCustomColors(
                            this, String.format("#%08X", color17), String.format("#%08X", color17));
                });
        binding.more.setOnClickListener(
                v -> {
                    ColorPickerDialog.newBuilder()
                            .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
                            .setAllowPresets(false)
                            .setDialogId(DIALOG_ID)
                            .setColor(Color.BLACK)
                            .setShowAlphaSlider(true)
                            .show(this);
                });
    }

    @Override
    public void onColorSelected(int dialogId, int color) {
        Log.d(
                TAG,
                "onColorSelected() called with: dialogId = ["
                        + dialogId
                        + "], color = ["
                        + color
                        + "]");
        switch (dialogId) {
            case DIALOG_ID:
                ThemeCustomiser.applyCustomColors(
                        getApplicationContext(),
                        Integer.toHexString(color),
                        Integer.toHexString(color));
                break;
        }
    }

    @Override
    public void onDialogDismissed(int dialogId) {
        Log.d(TAG, "onDialogDismissed() called with: dialogId = [" + dialogId + "]");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}