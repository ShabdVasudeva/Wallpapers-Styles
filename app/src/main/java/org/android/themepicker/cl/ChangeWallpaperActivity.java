package org.android.themepicker.cl;

import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.android.themepicker.cl.databinding.ActivityChangeWallpaperBinding;

public class ChangeWallpaperActivity extends AppCompatActivity
        implements ColorPickerDialogListener {
    private ActivityChangeWallpaperBinding binding;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int DIALOG_ID = 0;
    private static final int DIALOG_ID_T = 1;
    private static final String TAG = "MainActivity";
    private LiveWallpaperAdapter adapter;

    private int colorToString(String color) {
        return Color.parseColor(color);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangeWallpaperBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<ResolveInfo> liveWalls = getInstalledLiveWallpapers();
        adapter = new LiveWallpaperAdapter(this, liveWalls);
        binding.recyclerView.setAdapter(adapter);
        binding.toolbar.setNavigationOnClickListener(
                v -> {
                    onBackPressed();
                });
        binding.selectFromGallery.setOnClickListener(
                v -> {
                    openImagePicker();
                });
        binding.selector.setOnClickListener(
                v -> {
                    ColorPickerDialog.newBuilder()
                            .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
                            .setAllowPresets(false)
                            .setDialogId(DIALOG_ID)
                            .setColor(Color.BLACK)
                            .setShowAlphaSlider(true)
                            .show(this);
                });
        binding.selector2.setOnClickListener(
                v -> {
                    ColorPickerDialog.newBuilder()
                            .setDialogType(ColorPickerDialog.TYPE_CUSTOM)
                            .setAllowPresets(false)
                            .setDialogId(DIALOG_ID_T)
                            .setColor(Color.BLACK)
                            .setShowAlphaSlider(true)
                            .show(this);
                });
        binding.apply.setOnClickListener(
                v -> {
                    if (!binding.edit.getText().toString().contains("Select")
                            && !binding.edit2.getText().toString().contains("Select")) {
                        try {
                            setWallpaperWithGradientColors(
                                    colorToString(binding.edit.getText().toString()),
                                    colorToString(binding.edit2.getText().toString()));
                        } catch (Exception err) {
                            Toast.makeText(
                                            getApplicationContext(),
                                            "An error occured",
                                            Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.drawable.lineage1);
        arrayList.add(R.drawable.lineage2);
        arrayList.add(R.drawable.lineage3);
        arrayList.add(R.drawable.lineage4);
        arrayList.add(R.drawable.lineage5);
        arrayList.add(R.drawable.lineage6);
        CarouselAdapter adapter2 = new CarouselAdapter(this, arrayList);
        binding.recycler.setAdapter(adapter2);
        binding.more.setOnClickListener(
                v -> {
                    try {
                        startActivity(new Intent(this, MoreActivity.class));
                    } catch (Exception err) {
                        Toast.makeText(
                                        getApplicationContext(),
                                        "An error occured",
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                });
    }

    private Bitmap resizeBitmap(Bitmap bitmap, int targetWidth, int targetHeight) {
        return Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, true);
    }

    private void setWallpaperWithGradientColors(int startColor, int endColor) {
        GradientDrawable gradientDrawable =
                new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM, new int[] {startColor, endColor});
        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        gradientDrawable.setBounds(0, 0, 100, 100);
        gradientDrawable.draw(new Canvas(bitmap));
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        Bitmap resizedBitmap = resizeBitmap(bitmap, screenWidth, screenHeight);
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Set wallpaper as")
                .setNegativeButton(
                        "Lockscreen",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    wallpaperManager.setBitmap(
                                            resizedBitmap, null, true, WallpaperManager.FLAG_LOCK);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                .setPositiveButton(
                        "Homescreen",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    wallpaperManager.setBitmap(
                                            resizedBitmap,
                                            null,
                                            true,
                                            WallpaperManager.FLAG_SYSTEM);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                .setNeutralButton(
                        "Both",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    wallpaperManager.setBitmap(resizedBitmap);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                .show();
    }

    private void openImagePicker() {
        Intent pickImageIntent =
                new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImageIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
            builder.setTitle("Set wallpaper as")
                    .setNegativeButton(
                            "Lockscreen",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setWallpaperLock(selectedImageUri);
                                }
                            })
                    .setPositiveButton(
                            "Homescreen",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setWallpaper(selectedImageUri);
                                }
                            })
                    .setNeutralButton(
                            "Both",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setWallpaperBoth(selectedImageUri);
                                }
                            })
                    .show();
        }
    }

    private void setWallpaper(Uri imageUri) {
        try {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            wallpaperManager.setStream(
                    getContentResolver().openInputStream(imageUri),
                    null,
                    false,
                    WallpaperManager.FLAG_SYSTEM);
            Toast.makeText(this, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
        }
    }

    private void setWallpaperLock(Uri imageUri) {
        try {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            wallpaperManager.setStream(
                    getContentResolver().openInputStream(imageUri),
                    null,
                    false,
                    WallpaperManager.FLAG_LOCK);
            Toast.makeText(this, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
        }
    }

    private void setWallpaperBoth(Uri imageUri) {
        try {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            wallpaperManager.setStream(
                    getContentResolver().openInputStream(imageUri),
                    null,
                    false,
                    WallpaperManager.FLAG_LOCK);
            wallpaperManager.setStream(
                    getContentResolver().openInputStream(imageUri),
                    null,
                    false,
                    WallpaperManager.FLAG_SYSTEM);
            Toast.makeText(this, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
        }
    }

    private List<ResolveInfo> getInstalledLiveWallpapers() {
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent(WallpaperService.SERVICE_INTERFACE);
        return packageManager.queryIntentServices(intent, PackageManager.GET_META_DATA);
    }

    private void success() {
        Toast.makeText(this, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
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
                binding.edit.setText("#" + Integer.toHexString(color));
                break;
            case DIALOG_ID_T:
                binding.edit2.setText("#" + Integer.toHexString(color));
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
