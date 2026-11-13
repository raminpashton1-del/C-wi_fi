package com.capitan.wifi;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.*;
import android.view.View;
import android.graphics.drawable.GradientDrawable;

public class MainActivity extends Activity {

    LinearLayout root;
    TextView status;
    ProgressBar progress;
    Button start;

    int gold = Color.rgb(230, 180, 65);
    int purple = Color.rgb(115, 65, 180);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(Color.rgb(10,10,15));
        getWindow().setNavigationBarColor(Color.BLACK);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(24, 35, 24, 25);
        root.setBackgroundColor(Color.rgb(10,10,15));

        ScrollView scroll = new ScrollView(this);
        scroll.addView(root);

        setContentView(scroll);

        TextView logo = new TextView(this);
        logo.setText("ᎧᎮ ᭄Cₐₚᵢₜₐₙ亗wi_fi");
        logo.setTextColor(gold);
        logo.setTextSize(25);
        logo.setTypeface(Typeface.DEFAULT_BOLD);
        logo.setGravity(Gravity.CENTER);

        root.addView(logo);

        TextView subtitle = new TextView(this);
        subtitle.setText("\nWIRELESS LAB • SAFE SIMULATOR\n");
        subtitle.setTextColor(Color.LTGRAY);
        subtitle.setTextSize(13);
        subtitle.setGravity(Gravity.CENTER);

        root.addView(subtitle);

        addTitle("NETWORK DEMONSTRATION");

        addNetwork("CAPITAN_DEMO_01", "SAMPLE NETWORK");
        addNetwork("HOME_WIFI_SAMPLE", "SAMPLE NETWORK");
        addNetwork("GUEST_WIFI_SAMPLE", "SAMPLE NETWORK");
        addNetwork("TEST_ACCESS_POINT", "SAMPLE NETWORK");

        status = new TextView(this);
        status.setText("\nSYSTEM READY\n");
        status.setTextColor(gold);
        status.setTextSize(16);
        status.setGravity(Gravity.CENTER);

        root.addView(status);

        progress = new ProgressBar(this, null,
                android.R.attr.progressBarStyleHorizontal);

        progress.setMax(100);
        progress.setProgress(0);

        LinearLayout.LayoutParams pp =
                new LinearLayout.LayoutParams(-1, 15);

        pp.setMargins(0, 20, 0, 20);
        root.addView(progress, pp);

        start = new Button(this);
        start.setText("START SAFE DEMO");
        start.setTextColor(Color.BLACK);
        start.setBackgroundColor(gold);

        root.addView(start);

        TextView footer = new TextView(this);
        footer.setText(
                "\n━━━━━━━━━━━━━━━━━━━━\n" +
                "CAPITAN TECHNOLOGY\n" +
                "EDUCATIONAL SIMULATION ONLY\n" +
                "━━━━━━━━━━━━━━━━━━━━"
        );

        footer.setTextColor(purple);
        footer.setGravity(Gravity.CENTER);
        footer.setTextSize(12);

        root.addView(footer);

        start.setOnClickListener(v -> runDemo());
    }

    private void addTitle(String text) {
        TextView t = new TextView(this);
        t.setText("\n" + text + "\n");
        t.setTextColor(gold);
        t.setTextSize(17);
        t.setTypeface(null, Typeface.BOLD);
        root.addView(t);
    }

    private void addNetwork(String name, String type) {
        TextView item = new TextView(this);

        item.setText("  ◈  " + name +
                "\n       " + type);

        item.setTextColor(Color.WHITE);
        item.setTextSize(14);
        item.setPadding(15, 15, 15, 15);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.rgb(27, 25, 38));
        bg.setCornerRadius(18);
        bg.setStroke(1, purple);

        item.setBackground(bg);

        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(-1, -2);

        lp.setMargins(0, 7, 0, 7);

        root.addView(item, lp);
    }

    private void runDemo() {
        start.setEnabled(false);
        progress.setProgress(0);

        status.setText("DEMO INITIALIZING...");

        new Thread(() -> {
            String[] steps = {
                    "Preparing simulation...",
                    "Loading sample networks...",
                    "Analyzing demonstration data...",
                    "Completing simulation..."
            };

            for (String step : steps) {
                runOnUiThread(() -> status.setText(step));

                for (int i = 0; i <= 100; i += 5) {
                    final int value = i;

                    runOnUiThread(() ->
                            progress.setProgress(value));

                    try {
                        Thread.sleep(70);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }

            runOnUiThread(() -> {
                status.setText(
                        "DEMO COMPLETED\n" +
                        "NO PASSWORD CRACKED\n" +
                        "NO PRIVATE DATA ACCESSED"
                );

                start.setText("RUN AGAIN");
                start.setEnabled(true);
            });

        }).start();
    }
}
