package org.android.themepicker.cl;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.ParcelFileDescriptor;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.io.IOException;

public class StylesModel extends ViewModel {
    private MutableLiveData<Drawable> lockScreenWallpaper = new MutableLiveData<>();
    private MutableLiveData<Drawable> homeScreenWallpaper = new MutableLiveData<>();

    public LiveData<Drawable> getLockScreenWallpaper() {
        return lockScreenWallpaper;
    }

    public LiveData<Drawable> getHomeScreenWallpaper() {
        return homeScreenWallpaper;
    }

    public Drawable retrieveLockScreenWallpaper(Context ctxt) {
        Drawable wallpaper = ctxt.getResources().getDrawable(R.drawable.wallpaper);
        WallpaperManager manager = WallpaperManager.getInstance(ctxt);
        try {
            ParcelFileDescriptor descriptor = manager.getWallpaperFile(WallpaperManager.FLAG_LOCK);
            if (descriptor != null) {
                Bitmap bitmap = BitmapFactory.decodeFileDescriptor(descriptor.getFileDescriptor());
                wallpaper = new BitmapDrawable(ctxt.getResources(), bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wallpaper;
    }

    public Drawable retrieveHomeScreenWallpaper(Context ctxt) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(ctxt);
        Drawable wallpaperDrawable = wallpaperManager.getDrawable();
        return wallpaperDrawable;
    }
}
