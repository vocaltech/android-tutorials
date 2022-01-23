package fr.vocaltech.android.tutorials.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.vocaltech.android.tutorials.databinding.FragmentFirstBinding;


public class FirstFragment extends Fragment {
    private static final String TAG = "FirstFragment";
    private FragmentFirstBinding binding;

    public static FirstFragment newInstance(String text) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString("displayText", text);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = FragmentFirstBinding.inflate(getLayoutInflater());

        String displayText = getArguments().getString("displayText");
        binding.text.setText(displayText);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //
        // --- button listener ---
        //
        binding.firstButton.setOnClickListener(v -> Toast.makeText(getActivity(),"First fragment", Toast.LENGTH_LONG).show());

        //
        // --- FragmentResult listener ---
        //
        /*
                getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String res = result.getString("bundleKey");
                Log.d(TAG, "onFragmentResult: data from SecondFragment: " + res);
            }
        });
         */
        getParentFragmentManager().setFragmentResultListener("requestKey", this, (requestKey, result) -> {
            String res = result.getString("bundleKey");
            Log.d(TAG, "onFragmentResult: data from SecondFragment: " + res);
        });
    }

    public void updateText(String newText) {
        binding.text.setText(newText);
    }
}
