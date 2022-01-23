package fr.vocaltech.android.tutorials.binding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import fr.vocaltech.android.tutorials.databinding.ActivityMainDataBinding;

public class DataBindingMainActivity extends AppCompatActivity {
    ActivityMainDataBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // data settings
        binding.setHello("Greetings from data binding v2.0 !");


    }
}
