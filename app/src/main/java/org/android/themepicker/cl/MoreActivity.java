package org.android.themepicker.cl;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import org.android.themepicker.cl.databinding.ActivityMoreBinding;

public class MoreActivity extends AppCompatActivity {
    private ActivityMoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setNavigationOnClickListener(
                v -> {
                    onBackPressed();
                });
        if (!NetworkUtils.isInternetAvailable(this)) {
            Toast.makeText(
                            getApplicationContext(),
                            "Please turn your internet or wifi connection",
                            Toast.LENGTH_LONG)
                    .show();
        }
        ArrayList<String> evox = new ArrayList<>();
        evox.add(
                "https://raw.githubusercontent.com/ShabdVasudeva/Styles-Database/refs/heads/main/CypherLight.png");
        evox.add(
                "https://raw.githubusercontent.com/ShabdVasudeva/Styles-Database/refs/heads/main/Radiant-Light.png");
        evox.add(
                "https://raw.githubusercontent.com/ShabdVasudeva/Styles-Database/refs/heads/main/Evo-X_2.0_Light.png");
        evox.add(
                "https://raw.githubusercontent.com/ShabdVasudeva/Styles-Database/refs/heads/main/DarkPinkPastel.png");
        evox.add(
                "https://raw.githubusercontent.com/ShabdVasudeva/Styles-Database/refs/heads/main/FrostedDay.png");
        evox.add(
                "https://raw.githubusercontent.com/ShabdVasudeva/Styles-Database/refs/heads/main/DarkPurplePastel.png");
        NewCarouselAdapter adapter = new NewCarouselAdapter(this, evox);
        binding.evox.setAdapter(adapter);
        ArrayList<String> oneui = new ArrayList<>();
        oneui.add(
                "https://raw.githubusercontent.com/ShabdVasudeva/Styles-Database/refs/heads/main/One%20UI%205%20Wallpaper%201.jpg");
        oneui.add(
                "https://raw.githubusercontent.com/ShabdVasudeva/Styles-Database/refs/heads/main/One%20UI%205%20Wallpaper%202.jpg");
        oneui.add(
                "https://raw.githubusercontent.com/ShabdVasudeva/Styles-Database/refs/heads/main/One%20UI%205%20Wallpaper%203.jpg");
        oneui.add(
                "https://raw.githubusercontent.com/ShabdVasudeva/Styles-Database/refs/heads/main/One%20UI%205%20Wallpaper%206.jpg");
        oneui.add(
                "https://raw.githubusercontent.com/ShabdVasudeva/Styles-Database/refs/heads/main/One%20UI%205%20Wallpaper%207.jpg");
        NewCarouselAdapter oneuiAdapter = new NewCarouselAdapter(this, oneui);
        binding.oneui.setAdapter(oneuiAdapter);
        ArrayList<String> pixelexp = new ArrayList<>();
        pixelexp.add(
                "https://raw.githubusercontent.com/ShabdVasudeva/Styles-Database/refs/heads/main/pixel1.jpg");
        pixelexp.add(
                "https://raw.githubusercontent.com/ShabdVasudeva/Styles-Database/refs/heads/main/pixel2.png");
        pixelexp.add(
                "https://raw.githubusercontent.com/ShabdVasudeva/Styles-Database/refs/heads/main/pixel3.png");
        pixelexp.add(
                "https://raw.githubusercontent.com/ShabdVasudeva/Styles-Database/refs/heads/main/pixel4.png");
        pixelexp.add(
                "https://raw.githubusercontent.com/ShabdVasudeva/Styles-Database/refs/heads/main/pixel5.png");
        NewCarouselAdapter pixelAdapter = new NewCarouselAdapter(this, pixelexp);
        binding.pixelexp.setAdapter(pixelAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
