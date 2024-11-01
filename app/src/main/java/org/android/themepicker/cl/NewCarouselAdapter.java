package org.android.themepicker.cl;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewCarouselAdapter extends RecyclerView.Adapter<NewCarouselAdapter.ViewHolder> {
    private ArrayList<String> arrayList;
    private Context context;

    public NewCarouselAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;

        public ViewHolder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.image);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(arrayList.get(position)).into(holder.image);
        holder.itemView.setOnClickListener(
                v -> {
                    new MaterialAlertDialogBuilder(context)
                            .setTitle("Set Wallpaper As")
                            .setNegativeButton(
                                    "LockScreen",
                                    (dialog, which) -> setWallpaperAsLock(arrayList.get(position)))
                            .setPositiveButton(
                                    "HomeScreen",
                                    (dialog, which) -> setWallpaperAsHome(arrayList.get(position)))
                            .setNeutralButton(
                                    "Both",
                                    (dialog, which) -> setWallpaperAsBoth(arrayList.get(position)))
                            .show();
                });
    }

    private void setWallpaperAsHome(String imageUrl) {
        Glide.with(context)
                .asBitmap()
                .load(imageUrl)
                .into(
                        new CustomTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(
                                    @NonNull Bitmap resource,
                                    Transition<? super Bitmap> transition) {
                                try {
                                    WallpaperManager wallpaperManager =
                                            WallpaperManager.getInstance(context);
                                    wallpaperManager.setBitmap(
                                            resource, null, false, WallpaperManager.FLAG_SYSTEM);
                                    Toast.makeText(
                                                    context,
                                                    "Wallpaper set successfully",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    Toast.makeText(
                                                    context,
                                                    "Failed to set wallpaper",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }

                            @Override
                            public void onLoadCleared(Drawable placeHolder) {}
                        });
    }

    private void setWallpaperAsLock(String imageUrl) {
        Glide.with(context)
                .asBitmap()
                .load(imageUrl)
                .into(
                        new CustomTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(
                                    @NonNull Bitmap resource,
                                    Transition<? super Bitmap> transition) {
                                try {
                                    WallpaperManager wallpaperManager =
                                            WallpaperManager.getInstance(context);
                                    wallpaperManager.setBitmap(
                                            resource, null, false, WallpaperManager.FLAG_LOCK);
                                    Toast.makeText(
                                                    context,
                                                    "Wallpaper set successfully",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    Toast.makeText(
                                                    context,
                                                    "Failed to set wallpaper",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }

                            @Override
                            public void onLoadCleared(Drawable placeHolder) {}
                        });
    }

    private void setWallpaperAsBoth(String imageUrl) {
        Glide.with(context)
                .asBitmap()
                .load(imageUrl)
                .into(
                        new CustomTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(
                                    @NonNull Bitmap resource,
                                    Transition<? super Bitmap> transition) {
                                try {
                                    WallpaperManager wallpaperManager =
                                            WallpaperManager.getInstance(context);
                                    wallpaperManager.setBitmap(
                                            resource, null, false, WallpaperManager.FLAG_LOCK);
                                    wallpaperManager.setBitmap(
                                            resource, null, false, WallpaperManager.FLAG_SYSTEM);
                                    Toast.makeText(
                                                    context,
                                                    "Wallpaper set successfully",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    Toast.makeText(
                                                    context,
                                                    "Failed to set wallpaper",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }

                            @Override
                            public void onLoadCleared(Drawable placeHolder) {}
                        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carousel_two, parent, false);
        return new ViewHolder(view);
    }
}
