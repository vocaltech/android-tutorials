package fr.vocaltech.android.tutorials.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.vocaltech.android.tutorials.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {
    private static final String TAG = "SecondFragment";
    private FragmentSecondBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.secondButton.setOnClickListener(v -> {
            //
            // --- Pass data to FragmentManager ---
            //
            Bundle result = new Bundle();
            result.putString("bundleKey", "This is a data from SecondFragment !");
            getParentFragmentManager().setFragmentResult("requestKey", result);

            //
            // --- show Toast ---
            //
            Toast.makeText(getActivity(), "Second fragment", Toast.LENGTH_LONG)
                    .show();
        });
    }
}
