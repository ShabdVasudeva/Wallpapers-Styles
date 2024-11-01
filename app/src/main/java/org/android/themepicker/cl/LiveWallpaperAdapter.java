package org.android.themepicker.cl;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LiveWallpaperAdapter extends RecyclerView.Adapter<LiveWallpaperAdapter.ViewHolder> {
    private final List<ResolveInfo> liveWallpapers;
    private final PackageManager packageManager;
    private final Context context;

    public LiveWallpaperAdapter(Context context, List<ResolveInfo> liveWallpapers) {
        this.context = context;
        this.liveWallpapers = liveWallpapers;
        this.packageManager = context.getPackageManager();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_wallpaper, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResolveInfo wallpaper = liveWallpapers.get(position);
        holder.wallpaperName.setText(wallpaper.loadLabel(packageManager));
        holder.wallpaperIcon.setImageDrawable(wallpaper.loadIcon(packageManager));
        holder.itemView.setOnClickListener(
                v -> {
                    Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                    intent.putExtra(
                            WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                            new ComponentName(
                                    wallpaper.serviceInfo.packageName, wallpaper.serviceInfo.name));
                    context.startActivity(intent);
                });
    }

    @Override
    public int getItemCount() {
        return liveWallpapers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView wallpaperName;
        ImageView wallpaperIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            wallpaperName = itemView.findViewById(R.id.wallpaper_name);
            wallpaperIcon = itemView.findViewById(R.id.wallpaper_icon);
        }
    }
}
