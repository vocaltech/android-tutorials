package fr.vocaltech.android.tutorials.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import fr.vocaltech.android.tutorials.R;
import fr.vocaltech.android.tutorials.databinding.ActivityMainNotificationBinding;

public class NotificationMainActivity extends AppCompatActivity {
    private static final String TAG = "NotificationActivity";

    private NotificationManagerCompat notificationManagerCompat;
    private ActivityMainNotificationBinding binding;

    public static final String CHANNEL_ID = "channel_notification_activity_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: savedInstance state: " + savedInstanceState);

        binding = ActivityMainNotificationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnCreateNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Create notification...");
                sendNotification(view);
            }
        });

        binding.btnCancelNotification.setEnabled(false);
        binding.btnCancelNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Cancel notification...");
                cancelNotification(view);
            }
        });

        // --- notification section ---
        createNotificationChannel();
        this.notificationManagerCompat = NotificationManagerCompat.from(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Log.d(TAG, "onBackPressed: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart...");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy...");
    }

    public void sendNotification(View view) {
        // --- UI section ---
        binding.btnCreateNotification.setEnabled(false);
        binding.btnCancelNotification.setEnabled(true);

        // --- intent settings --
        Intent intent = new Intent(this, NotificationMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP); // to save the UI state
        PendingIntent pendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        }

        // --- notification settings ---
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_car_location_notification)
                .setContentTitle("title")
                .setContentText("message")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        int notificationId = 1;
        this.notificationManagerCompat.notify(notificationId, notification);
    }

    public void cancelNotification(View view) {
        binding.btnCreateNotification.setEnabled(true);
        binding.btnCancelNotification.setEnabled(false);

        notificationManagerCompat.cancel(1);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "ChannelMainActivity",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("This is channel for MainActivity");

            NotificationManager manager = this.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}