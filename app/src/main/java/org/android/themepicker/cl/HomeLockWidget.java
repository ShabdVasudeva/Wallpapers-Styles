package org.android.themepicker.cl;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.ParcelFileDescriptor;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.button.MaterialButton;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HomeLockWidget extends LinearLayout {
    private LinearLayout home, lock;
    private TextView time, date;
    private Button button;

    public HomeLockWidget(Context ctxt) {
        super(ctxt);
        initialize(ctxt);
    }

    public HomeLockWidget(Context ctxt, AttributeSet attrs) {
        super(ctxt, attrs);
        initialize(ctxt);
    }

    private void initialize(Context ctxt) {
        LayoutInflater.from(ctxt).inflate(R.layout.home_lock, this, true);
        lock = findViewById(R.id.lock);
        home = findViewById(R.id.home);
        date = findViewById(R.id.lock_date);
        time = findViewById(R.id.lock_time);
        button = findViewById(R.id.textButton);
        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh\nmm", Locale.getDefault());
        time.setText(sdf.format(now));
        date.setText(
                cal.get(Calendar.DATE)
                        + ", "
                        + cal.get(Calendar.MONTH)
                        + ", "
                        + cal.get(Calendar.YEAR));
    }

    public void setLockWallpaper(Drawable wallpaper) {
        lock.setBackground(wallpaper);
    }

    public void setOnIconClickListener(OnClickListener onClick) {
        button.setOnClickListener(onClick);
    }

    public void setHomeWallpaper(Drawable wallpaperDrawable) {
        home.setBackground(wallpaperDrawable);
    }
}
