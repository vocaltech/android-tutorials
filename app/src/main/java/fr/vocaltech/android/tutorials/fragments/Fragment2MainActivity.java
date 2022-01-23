package fr.vocaltech.android.tutorials.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import fr.vocaltech.android.tutorials.R;
import fr.vocaltech.android.tutorials.databinding.ActivityMainFragment2Binding;

public class Fragment2MainActivity extends AppCompatActivity {
    private static final String TAG = "Fragment2MainActivity";
    private ActivityMainFragment2Binding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainFragment2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.firstFragment.setOnClickListener(v -> loadFragment(FirstFragment.newInstance("Welcome to FirstFragment"), "FIRST_FRAGMENT"));

        binding.secondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new SecondFragment(), "SECOND_FRAGMENT");
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        FirstFragment firstFragment = (FirstFragment) getSupportFragmentManager().findFragmentByTag("FIRST_FRAGMENT");
        firstFragment.updateText("Welcome again to FirstFragment !");
    }

    private void loadFragment(Fragment fragment, String tag) {
        // create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();

        // create a FragmentTransaction to begin the transaction and replace the Fragment
        fm.beginTransaction()
            .replace(R.id.fragment_container, fragment, tag)
            .setReorderingAllowed(true)
            .addToBackStack("name") // name can be null
            .commit();
    }
}
