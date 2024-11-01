package org.android.themepicker.cl;
import android.app.Application;
import com.google.android.material.color.DynamicColors;

public class ApplicationUtils extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);
    }
}
