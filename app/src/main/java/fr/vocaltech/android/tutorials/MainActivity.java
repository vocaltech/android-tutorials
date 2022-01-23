package fr.vocaltech.android.tutorials;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import fr.vocaltech.android.tutorials.binding.DataBindingMainActivity;
import fr.vocaltech.android.tutorials.fragments.Fragment2MainActivity;
import fr.vocaltech.android.tutorials.fragments.Fragment1MainActivity;
import fr.vocaltech.android.tutorials.notifications.NotificationMainActivity;
import fr.vocaltech.android.tutorials.realm.RealmMainActivity;

import fr.vocaltech.android.tutorials.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityMainBinding binding;

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        
        binding.btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NotificationMainActivity.class);
                startActivity(intent);
            }
        });
        
        binding.btnFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: btnFragment1");
                Intent intent = new Intent(MainActivity.this, Fragment1MainActivity.class);
                startActivity(intent);
            }
        });
        
        binding.btnFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: btnFragment2");
                Intent intent = new Intent(MainActivity.this, Fragment2MainActivity.class);
                startActivity(intent);
            }
        });

        binding.btnRealm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RealmMainActivity.class);
                startActivity(intent);
            }
        });

        binding.btnDataBinding.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, DataBindingMainActivity.class));
        });
    }
}