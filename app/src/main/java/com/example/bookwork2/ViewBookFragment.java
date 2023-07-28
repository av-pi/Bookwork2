package com.example.bookwork2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bookwork2.databinding.FragmentViewBookBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ViewBookFragment extends BottomSheetDialogFragment {

    private static final String ARG_SELECTED_BOOK = "selectedBook";

    private FragmentViewBookBinding binding;
    private BookViewModel viewModel;

    public ViewBookFragment() {
        // Required empty public constructor
    }

    public static ViewBookFragment newInstance() {
        ViewBookFragment fragment = new ViewBookFragment();
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
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_book, container, false);
        this.viewModel = new ViewModelProvider(getActivity()).get(BookViewModel.class);

        // Retrieve the clicked item from the arguments
        Book selectedBook = getArguments().getParcelable(ARG_SELECTED_BOOK);
        if (selectedBook != null) {
            this.viewModel.setBook(selectedBook);
        }

        this.binding.setBookViewModel(this.viewModel);
        this.binding.setLifecycleOwner(this);

        return this.binding.getRoot();



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.viewModel.getImageURL().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String imageURL) {
                Glide.with(getActivity())
                        .asBitmap()
                        .load(imageURL)
                        .into(binding.bookCoverImageView);
            }
        });


    }
}