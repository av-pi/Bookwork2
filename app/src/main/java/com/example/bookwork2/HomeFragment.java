package com.example.bookwork2;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import com.example.bookwork2.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);

        binding.setHomeViewModel(viewModel);
        binding.setLifecycleOwner(this);

        // Set the OnEditorActionListener for the EditText
        binding.searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.performSearchByQuery();
                launchSearchResultsFragment();
                return true; // Return true to indicate that the action has been handled
            }
            return false; // Return false if you want to allow other actions to be handled
        });

        return binding.getRoot();

    }

    private void launchSearchResultsFragment() {
        SearchResultsFragment popupFragment = new SearchResultsFragment();
        popupFragment.show(getParentFragmentManager(), popupFragment.getTag());
    }


}