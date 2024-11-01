package org.android.themepicker.cl;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.palette.graphics.Palette;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.color.utilities.CorePalette;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import org.android.themepicker.cl.databinding.ActivityMainBinding;
import rikka.shizuku.Shizuku;
import rikka.shizuku.ShizukuBinderWrapper;
import rikka.shizuku.ShizukuProvider;
import rikka.shizuku.SystemServiceHelper;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private StylesModel util;
    private int color;
    private static final int READ_INTERNAL_STORAGE = 100;
    private int color1, color2, color3, color4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
                                this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[] {
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    },
                    READ_INTERNAL_STORAGE);
        } else {
            loadStyles();
        }
        binding.widgets.setOnIconClickListener(
                v -> {
                    try {
                        startActivity(new Intent(this, ChangeWallpaperActivity.class));
                    } catch (Exception err) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "An error occured",
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                });
        binding.more.setOnClickListener(
                v -> {
                    try {
                        startActivity(new Intent(this, MoreColorsActivity.class));
                    } catch (Exception err) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "An error occured",
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int arg0, String[] arg1, int[] arg2) {
        super.onRequestPermissionsResult(arg0, arg1, arg2);
        if (arg0 == READ_INTERNAL_STORAGE) {
            if (arg2.length > 0 && arg2[0] == PackageManager.PERMISSION_GRANTED) {
                loadStyles();
            } else {
                Toast.makeText(
                                this,
                                "Please grant the necessary permissions first",
                                Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    private void loadStyles() {
        util = new ViewModelProvider(this).get(StylesModel.class);
        binding.widgets.setHomeWallpaper(util.retrieveHomeScreenWallpaper(this));
        binding.widgets.setLockWallpaper(util.retrieveLockScreenWallpaper(this));
        Bitmap bitmap = ((BitmapDrawable) util.retrieveHomeScreenWallpaper(this)).getBitmap();
        Palette.from(bitmap)
                .generate(
                        new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette arg0) {
                                int lightMutedColor = arg0.getLightMutedColor(Color.BLACK);
                                int mutedColor = arg0.getMutedColor(Color.BLACK);
                                int lightVibrantColor = arg0.getLightVibrantColor(Color.BLACK);
                                int darkVibrantColor = arg0.getDarkVibrantColor(Color.BLACK);
                                color1 = lightMutedColor;
                                color2 = mutedColor;
                                color3 = lightVibrantColor;
                                color4 = darkVibrantColor;
                                binding.fab1.setBackgroundTintList(
                                        ColorStateList.valueOf(lightMutedColor));
                                binding.fab2.setBackgroundTintList(
                                        ColorStateList.valueOf(mutedColor));
                                binding.fab3.setBackgroundTintList(
                                        ColorStateList.valueOf(lightVibrantColor));
                                binding.fab4.setBackgroundTintList(
                                        ColorStateList.valueOf(darkVibrantColor));
                            }
                        });
        binding.fab1.setOnClickListener(
                v -> {
                    ThemeCustomiser.applyCustomColors(
                            getApplicationContext(),
                            String.format("#%08X", color1),
                            String.format("#%08X", color1));
                });
        binding.fab2.setOnClickListener(
                v -> {
                    ThemeCustomiser.applyCustomColors(
                            getApplicationContext(),
                            String.format("#%08X", color2),
                            String.format("#%08X", color2));
                });
        binding.fab3.setOnClickListener(
                v -> {
                    ThemeCustomiser.applyCustomColors(
                            getApplicationContext(),
                            String.format("#%08X", color3),
                            String.format("#%08X", color3));
                });
        binding.fab4.setOnClickListener(
                v -> {
                    ThemeCustomiser.applyCustomColors(
                            getApplicationContext(),
                            String.format("#%08X", color4),
                            String.format("#%08X", color4));
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
}
