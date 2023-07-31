package com.example.bookwork2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookwork2.databinding.FragmentSearchBinding;

import java.util.List;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private SearchViewModel viewModel;

    private MutableLiveData<List<Book>> searchedBooks;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_search, container, false);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(SearchViewModel.class);

        binding.setSearchViewModel(viewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.performAdvancedSearch();
                launchSearchResultsFragment();
            }
        });

    }

    private void launchSearchResultsFragment() {

        SearchResultsFragment popupFragment = new SearchResultsFragment();
        popupFragment.show(getParentFragmentManager(), popupFragment.getTag());
    }
}