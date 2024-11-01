package org.android.themepicker.cl;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.io.IOException;
import java.util.ArrayList;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.ViewHolder> {
    Context context;
    ArrayList<Integer> arrayList;

    public CarouselAdapter(Context context, ArrayList<Integer> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carousel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(arrayList.get(position));
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

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    private void setWallpaperAsHome(Integer drawableResId) {
        Drawable drawable =
                ResourcesCompat.getDrawable(context.getResources(), drawableResId, null);
        if (drawable != null) {
            Bitmap bitmap =
                    Bitmap.createBitmap(
                            drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight(),
                            Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            try {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
                wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_SYSTEM);
                Toast.makeText(context, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setWallpaperAsLock(Integer drawableResId) {
        Drawable drawable =
                ResourcesCompat.getDrawable(context.getResources(), drawableResId, null);
        if (drawable != null) {
            Bitmap bitmap =
                    Bitmap.createBitmap(
                            drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight(),
                            Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            try {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
                wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_LOCK);
                Toast.makeText(context, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setWallpaperAsBoth(Integer drawableResId) {
        Drawable drawable =
                ResourcesCompat.getDrawable(context.getResources(), drawableResId, null);
        if (drawable != null) {
            Bitmap bitmap =
                    Bitmap.createBitmap(
                            drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight(),
                            Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            try {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
                wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_LOCK);
                wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_SYSTEM);
                Toast.makeText(context, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.list_item_image);
        }
    }
}
