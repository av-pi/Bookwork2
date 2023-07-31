package com.example.bookwork2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookwork2.databinding.FragmentSearchBinding;

import java.util.List;

public class SearchViewModel extends AndroidViewModel {


    private BookRepository repository;
    private LiveData<List<Book>> searchedBooks;
    private MutableLiveData<String> title = new MutableLiveData<>();
    private MutableLiveData<String> author = new MutableLiveData<>();
    private MutableLiveData<String> genre = new MutableLiveData<>();
    private MutableLiveData<String> publisher = new MutableLiveData<>();

    public SearchViewModel(@NonNull Application application) {
        super(application);
        this.repository = new BookRepository(application);
    }

    public LiveData<List<Book>> getSearchedBooks() {

        if (this.searchedBooks == null) {
            return new MutableLiveData<List<Book>>();
        } else {
            return this.searchedBooks;
        }

    }

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public void setTitle(MutableLiveData<String> title) {
        this.title = title;
    }

    public MutableLiveData<String> getAuthor() {
        return author;
    }

    public void setAuthor(MutableLiveData<String> author) {
        this.author = author;
    }

    public MutableLiveData<String> getGenre() {
        return genre;
    }

    public void setGenre(MutableLiveData<String> genre) {
        this.genre = genre;
    }

    public MutableLiveData<String> getPublisher() {
        return publisher;
    }

    public void setPublisher(MutableLiveData<String> publisher) {
        this.publisher = publisher;
    }

    public void performAdvancedSearch() {
        this.repository.advancedSearch(title.getValue(),
                author.getValue(),
                genre.getValue(),
                publisher.getValue());

        this.searchedBooks = repository.getSearchedBooksLiveData();
    }

    public LiveData<List<Book>> getSearchedBooksLiveData() { return this.searchedBooks; }



}

