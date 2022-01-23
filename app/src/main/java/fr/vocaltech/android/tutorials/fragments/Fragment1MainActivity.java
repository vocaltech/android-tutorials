package fr.vocaltech.android.tutorials.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import fr.vocaltech.android.tutorials.databinding.ActivityMainFragment1Binding;

public class Fragment1MainActivity extends AppCompatActivity {
    private static final String TAG = "FragmentMainActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ActivityMainFragment1Binding binding;

        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate: ");

        binding = ActivityMainFragment1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}
