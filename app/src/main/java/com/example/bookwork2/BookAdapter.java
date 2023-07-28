package com.example.bookwork2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookwork2.databinding.BookListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private Context context;
    private List<Book> booksList;

    private NavController navController;

    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    public void setItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public BookAdapter() { this.booksList = new ArrayList<>(); }

    public BookAdapter(List<Book> books) {
        this.booksList = books;
    }

    public void setBooksList(List<Book> list) {
        this.booksList = list;
        notifyDataSetChanged();
    }

    public void setNavController(NavController navController) {
        this.navController = navController;
    }

    /* private static final DiffUtil.ItemCallback<Book> DIFF_CALLBACK = new DiffUtil.ItemCallback<Book>() {
        @Override
        public boolean areItemsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Book oldItem, @NonNull Book newItem) {
            return oldItem.equals(newItem);
        }
    };*/

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list_item, parent, false);
        return new BookViewHolder(itemView);*/

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        BookListItemBinding binding = BookListItemBinding.inflate(layoutInflater, parent, false);
        return new BookViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = booksList.get(position);
        holder.bind(book);

        holder.itemView.setOnClickListener(view -> {
            itemClickListener.onItemClick(book);
        });


        /*holder.bookTitle.setText(book.getTitle());
        holder.bookAuthor.setText(book.getAuthor());
        // Load the actual book cover image using an image loading library like Glide or Picasso.
        // For simplicity, let's just set the placeholder image for now.
        //holder.bookImage.setImageResource(R.drawable.ic_search);

        Glide.with(context)
                .asBitmap()
                .load(book.getImageURL())
                .into(holder.bookImage);

        // Set click listener for the save button (ImageButton)
        holder.saveButton.setOnClickListener(v -> {
            // Handle the save button click event here.
            // You can perform the save action for the corresponding book.
        });*/
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookTitle;
        TextView bookAuthor;
        ImageButton saveButton;

        private final BookListItemBinding binding;

        BookViewHolder(BookListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        void bind(Book book) {
            binding.setBook(book);
            binding.executePendingBindings();

            Glide.with(binding.bookImage.getContext())
                    .asBitmap()
                    .load(book.getImageURL())
                    .into(binding.bookImage);
        }

        /*BookViewHolder(View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.bookImage);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookAuthor = itemView.findViewById(R.id.bookAuthor);
            saveButton = itemView.findViewById(R.id.saveButton);
        }*/
    }

    
}
