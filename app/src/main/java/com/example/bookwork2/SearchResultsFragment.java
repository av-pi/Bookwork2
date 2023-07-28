package com.example.bookwork2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class SearchResultsFragment extends BottomSheetDialogFragment {

    private HomeViewModel viewModel;
    private BookAdapter bookAdapter;

    public SearchResultsFragment() {
        // Required empty public constructor
    }

    public static SearchResultsFragment newInstance(String param1, String param2) {
        SearchResultsFragment fragment = new SearchResultsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search_results, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);

        // Initialize the ViewModel
        viewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bookAdapter = new BookAdapter();

        bookAdapter.setItemClickListener(book -> {
            // Perform the navigation to BookDetailsFragment with the selected book as an argument
            launchViewBookFragment(book);
        });

        recyclerView.setAdapter(bookAdapter);


        return rootView;

    }

    private void launchViewBookFragment(Book book) {
        ViewBookFragment viewBookFragment = new ViewBookFragment();

        Bundle args = new Bundle();
        args.putParcelable("selectedBook", book);

        viewBookFragment.setArguments(args);

        viewBookFragment.show(getParentFragmentManager(), viewBookFragment.getTag());


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Observe the LiveData list of books from the ViewModel
        viewModel.getSearchedBooks().observe(getViewLifecycleOwner(), new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                // Update the adapter's data whenever the LiveData changes
                bookAdapter.setBooksList(books);

            }
        });
    }
}