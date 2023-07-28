package com.example.bookwork2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class BookViewModel extends AndroidViewModel {

    private BookRepository repository;

    public MutableLiveData<String> title;
    public MutableLiveData<String> author;

    public MutableLiveData<String> imageURL;

    public MutableLiveData<String> genre;

    public MutableLiveData<String> publisher;



    private Book book;

    public BookViewModel(@NonNull Application application) {
        super(application);
        this.repository = new BookRepository(application);

        //System.out.println(book.getTitle());

        //this.title.setValue(book.getTitle());
        //this.author.setValue(book.getAuthor());

    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {

        this.book = book;
        initFields();

    }

    private void initFields() {
        this.title = new MutableLiveData<>();
        this.author = new MutableLiveData<>();
        this.imageURL = new MutableLiveData<>();
        this.title.setValue(book.getTitle());
        this.author.setValue(book.getAuthor());
        this.imageURL.setValue(book.getImageURL());

    }

    public MutableLiveData<String> getImageURL() {
        return imageURL;
    }

    public void setImageURL(MutableLiveData<String> imageURL) {
        this.imageURL = imageURL;
    }
}
