package com.example.bookwork2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private BookRepository repository;
    private LiveData<List<Book>> searchedBooks;
    private MutableLiveData<String> query = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.repository = new BookRepository(application);
    }

    public MutableLiveData<String> getQuery() {
        return query;
    }


    public void insert(Book book) {
        this.repository.insert(book);
    }

    public void delete(Book book) {
        this.repository.delete(book);
    }

    public void update(Book book) {
        this.repository.update(book);
    }

    public void getShelf(String shelf) {
        this.repository.getShelf(shelf);
    }

    public void performSearchByQuery() {
        repository.searchByQuery(query.getValue());
        this.searchedBooks = repository.getSearchedBooksLiveData();
    }

    public LiveData<List<Book>> getSearchedBooks() {

        if (this.searchedBooks == null) {
            return new MutableLiveData<List<Book>>();
        } else {
            return this.searchedBooks;
        }
    }
}
