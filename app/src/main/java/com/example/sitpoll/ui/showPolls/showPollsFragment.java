package com.example.sitpoll.ui.showPolls;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sitpoll.R;

public class showPollsFragment extends Fragment {

    private showpollsViewModel showpollsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        showpollsViewModel =
                new ViewModelProvider(this).get(showpollsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_showpolls, container, false);

        showpollsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}